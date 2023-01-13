package kodlamaio.hrms.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.AuthService;
import kodlamaio.hrms.business.abstracts.EmployeeService;
import kodlamaio.hrms.business.abstracts.EmployerService;
import kodlamaio.hrms.business.abstracts.HrmsAdminService;
import kodlamaio.hrms.business.abstracts.UserService;
import kodlamaio.hrms.business.requests.employeeRequests.CreateEmployeeRequest;
import kodlamaio.hrms.business.requests.employerRequests.CreateEmployerRequest;
import kodlamaio.hrms.business.requests.employerRequests.UpdateEmployerRequest;
import kodlamaio.hrms.business.requests.hrmsAdminRequests.CreateHrmsAdminRequest;
import kodlamaio.hrms.business.responses.authResponses.AuthenticationResponse;
import kodlamaio.hrms.core.entities.User;
import kodlamaio.hrms.core.entities.enums.Role;
import kodlamaio.hrms.core.utilities.mapping.ModelMapperService;
import kodlamaio.hrms.core.utilities.results.DataResult;
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
	

	@Autowired
	public AuthManager(EmployeeService employeeService, EmployerService employerService,
			VerificationService verificationService, VerficationByHrmsService verficationByHrmsService,
			ModelMapperService modelMapper, UserService userService, HrmsAdminService hrmsAdminService,
			TokenHelper tokenHelper, PasswordEncoder passwordEncoder) {
		this.employeeService = employeeService;
		this.employerService = employerService;
		this.hrmsAdminService = hrmsAdminService;
		this.verificationService = verificationService;
		this.verficationByHrmsService = verficationByHrmsService;
		this.modelMapper = modelMapper;
		this.userService = userService;
		this.tokenHelper = tokenHelper;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public DataResult<AuthenticationResponse> registerEmployee(CreateEmployeeRequest employeeRequest) {
//		employeeRequest.setPassword(encodePassword(employeeRequest.getPassword()));
		
		employeeRequest.setUserRole(Role.EMPLOYEE);
//		User userToRegister = modelMapper.forRequest().map(employeeRequest, User.class);
//		tokenHelper.createToken(userToRegister);
		employeeService.add(employeeRequest);
		var user = User.builder()
				.email(employeeRequest.getEmail())
				.password(passwordEncoder.encode(employeeRequest.getPassword()))
				.role(Role.EMPLOYEE)
				.build();
				
		var jwtToken = tokenHelper.generateToken(user);
		var authenticationResponse = AuthenticationResponse.builder().token(jwtToken).build();
		return new SuccessDataResult<AuthenticationResponse>(authenticationResponse);
		

	}

	@Override
	public Result registerEmployer(CreateEmployerRequest employerRequest) {
		var result = employerService.add(employerRequest);
		if (!result.isSuccess()) {
			return new SuccessResult(result.getMessage());
		}
		return new SuccessResult(result.getMessage());

	}
	
	@Override
	public Result registerHrmsAdmin(CreateHrmsAdminRequest hrmsAdminRequest) {
		
		return this.hrmsAdminService.add(hrmsAdminRequest);
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
//	private String encodePassword(String password) {
//		String encodedPassword = bCryptPasswordEncoder.encode(password);
//		return encodedPassword;
//	}

}
