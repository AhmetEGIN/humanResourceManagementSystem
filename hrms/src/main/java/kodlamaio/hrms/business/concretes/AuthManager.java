package kodlamaio.hrms.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kodlamaio.hrms.business.abstracts.AuthService;
import kodlamaio.hrms.business.abstracts.EmployeeService;
import kodlamaio.hrms.business.abstracts.EmployerService;
import kodlamaio.hrms.business.abstracts.HrmsAdminService;
import kodlamaio.hrms.business.abstracts.UserService;
import kodlamaio.hrms.business.constants.messages.Message;
import kodlamaio.hrms.business.requests.authRequests.LoginRequest;
import kodlamaio.hrms.business.requests.employeeRequests.CreateEmployeeRequest;
import kodlamaio.hrms.business.requests.employerRequests.CreateEmployerRequest;
import kodlamaio.hrms.business.requests.employerRequests.UpdateEmployerRequest;
import kodlamaio.hrms.business.requests.hrmsAdminRequests.CreateHrmsAdminRequest;
import kodlamaio.hrms.business.responses.authResponses.AuthenticationResponse;
import kodlamaio.hrms.core.entities.User;
import kodlamaio.hrms.core.entities.enums.Role;
import kodlamaio.hrms.core.utilities.business.BusinessRules;
import kodlamaio.hrms.core.utilities.mapping.ModelMapperService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorDataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.core.utilities.security.TokenHelper;
import kodlamaio.hrms.core.utilities.verifications.VerficationByHrmsService;
import kodlamaio.hrms.core.utilities.verifications.VerificationService;

@Service
public class AuthManager implements AuthService {
	private EmployeeService employeeService;
	private EmployerService employerService;
	private HrmsAdminService hrmsAdminService;
	private VerificationService verificationService;
	private VerficationByHrmsService verficationByHrmsService;
	private ModelMapperService modelMapper;
	private UserService userService;
	private TokenHelper tokenHelper;
	private PasswordEncoder passwordEncoder;
	private AuthenticationManager authenticationManager;

	@Autowired
	public AuthManager(EmployeeService employeeService, EmployerService employerService,
			VerificationService verificationService, VerficationByHrmsService verficationByHrmsService,
			ModelMapperService modelMapper, UserService userService, HrmsAdminService hrmsAdminService,
			TokenHelper tokenHelper, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
		this.employeeService = employeeService;
		this.employerService = employerService;
		this.hrmsAdminService = hrmsAdminService;
		this.verificationService = verificationService;
		this.verficationByHrmsService = verficationByHrmsService;
		this.modelMapper = modelMapper;
		this.userService = userService;
		this.tokenHelper = tokenHelper;
		this.passwordEncoder = passwordEncoder;
		this.authenticationManager = authenticationManager;
	}

	@Override
	@Transactional
	public DataResult<AuthenticationResponse> registerEmployee(CreateEmployeeRequest employeeRequest) {
		var result = BusinessRules.run(
				isPasswordAndConfirmPasswordEqual(employeeRequest.getPassword(), employeeRequest.getConfirmPassword()),
				isEmailExists(employeeRequest.getEmail())
				);
		if (result != null) {
			return new ErrorDataResult<>(result.getMessage());
		}
		employeeRequest.setUserRole(Role.EMPLOYEE);
		employeeRequest.setPassword(passwordEncoder.encode(employeeRequest.getPassword()));
//		User userToRegister = modelMapper.forRequest().map(employeeRequest, User.class);
//		tokenHelper.createToken(userToRegister);
		
		var register = employeeService.add(employeeRequest);
		if (!register.isSuccess()) {
			return new ErrorDataResult<AuthenticationResponse>(register.getMessage());
		}
		var user = User.builder()
				.email(employeeRequest.getEmail())
				.password(employeeRequest.getPassword())
				.role(employeeRequest.getUserRole())
				.build();
				
		var jwtToken = tokenHelper.generateToken(user);
		var authenticationResponse = AuthenticationResponse.builder().token(jwtToken).build();
		return new SuccessDataResult<AuthenticationResponse>(authenticationResponse);
		

	}

	@Override
	@Transactional
	public DataResult<AuthenticationResponse> registerEmployer(CreateEmployerRequest employerRequest) {
		var result = BusinessRules.run(
				isPasswordAndConfirmPasswordEqual(employerRequest.getPassword(), employerRequest.getConfirmPassword()),
				isEmailExists(employerRequest.getEmail())
				);
		if (result != null) {
			return new ErrorDataResult<>(result.getMessage());
		}
		employerRequest.setUserRole(Role.EMPLOYER);
		employerRequest.setPassword(passwordEncoder.encode(employerRequest.getPassword()));
		employerService.add(employerRequest);
		var user = User.builder()
				.email(employerRequest.getEmail())
				.password(employerRequest.getPassword())
				.role(employerRequest.getUserRole())
				.build();
				
		var jwtToken = tokenHelper.generateToken(user);
		var authenticationResponse = AuthenticationResponse.builder().token(jwtToken).build();
		return new SuccessDataResult<AuthenticationResponse>(authenticationResponse);

	}
	
	@Override
	@Transactional
	public DataResult<AuthenticationResponse> registerHrmsAdmin(CreateHrmsAdminRequest hrmsAdminRequest) {
		hrmsAdminRequest.setUserRole(Role.ADMİN);
		hrmsAdminRequest.setPassword(passwordEncoder.encode(hrmsAdminRequest.getPassword()));
		hrmsAdminService.add(hrmsAdminRequest);
		var user = User.builder()
				.email(hrmsAdminRequest.getEmail())
				.password(hrmsAdminRequest.getPassword())
				.role(hrmsAdminRequest.getUserRole())
				.build();
				
		var jwtToken = tokenHelper.generateToken(user);
		var authenticationResponse = AuthenticationResponse.builder().token(jwtToken).build();
		return new SuccessDataResult<AuthenticationResponse>(authenticationResponse);

	}
	
	public DataResult<AuthenticationResponse> login(LoginRequest loginRequest){
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
		var user = this.userService.getByEmail(loginRequest.getEmail());
		if (!user.isSuccess()) {
			return new ErrorDataResult<AuthenticationResponse>(user.getMessage());
		}
		if (!passwordEncoder.encode(loginRequest.getPassword()).equals(user.getData().getPassword())) {
			return new ErrorDataResult<AuthenticationResponse>(Message.WRONG_PASSWORD);
		}
		

		
		String jwtToken = tokenHelper.generateToken(user.getData());
		AuthenticationResponse authenticationResponse = new AuthenticationResponse(jwtToken);
		return new SuccessDataResult<AuthenticationResponse>(authenticationResponse, Message.LOGIN_SUCCESSFUL);
		
	}

	@Override
	public Result verifyEmail(int userId) {
		if (verificationService.verifyEmail().isSuccess()) {
			Result result = this.userService.setVerify(userId);
			return result;
		}
		return new ErrorResult();

	}

	@Override
	public Result verifyEmployer(int userId) {
		if (verficationByHrmsService.isVerifiedFromHrms().isSuccess()) {
			UpdateEmployerRequest employerRequest = modelMapper.forResponse().map(employerService.getById(userId).getData(),
					UpdateEmployerRequest.class);
			employerRequest.setVerified(true);
			var result = employerService.update(employerRequest);
			return new SuccessResult(result.getMessage());
		}
		return new ErrorResult();
	}

	
	
	
	// private codes
	private Result isPasswordAndConfirmPasswordEqual(String password, String confirmPassword) {
		if (password.equals(confirmPassword)) {
			return new SuccessResult();
		}
		return new ErrorResult(Message.passwordAndConfirmPasswordNotMatched);
	}
	
	private Result isEmailExists(String email) {
		Result result = this.userService.isEmailExists(email);
		return result;
		
	}

}
