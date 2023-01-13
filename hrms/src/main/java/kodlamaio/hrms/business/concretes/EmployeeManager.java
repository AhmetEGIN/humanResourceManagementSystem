package kodlamaio.hrms.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.EmployeeService;
import kodlamaio.hrms.business.adapters.mernis.UserCheckService;
import kodlamaio.hrms.business.constants.messages.Message;
import kodlamaio.hrms.business.requests.employeeRequests.CreateEmployeeRequest;
import kodlamaio.hrms.business.requests.employeeRequests.UpdateEmployeeRequest;
import kodlamaio.hrms.business.responses.employeeResponses.GetAllEmployeeResponse;
import kodlamaio.hrms.business.responses.employeeResponses.GetEmployeeResponse;
import kodlamaio.hrms.core.utilities.business.BusinessRules;
import kodlamaio.hrms.core.utilities.mapping.ModelMapperService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.EmployeeRepository;
import kodlamaio.hrms.entities.concretes.Employee;

@Service
public class EmployeeManager implements EmployeeService {
	private EmployeeRepository employeeRepository;
	private UserCheckService userCheckService;
	private ModelMapperService mapperService;
	private PasswordEncoder passwordEncoder;
	@Autowired
	public EmployeeManager(EmployeeRepository employeeRepository, UserCheckService userCheckService,
			ModelMapperService mapperService, PasswordEncoder passwordEncoder) {
		this.employeeRepository = employeeRepository;
		this.userCheckService = userCheckService;
		this.mapperService = mapperService;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public Result add(CreateEmployeeRequest employeeRequest) {
//		List<Result> rules = new ArrayList<Result>();
//		Collections.addAll(rules, isRequiredFieldsEmpty(employeeRequest), isPersonReal(employeeRequest),
//				isEmailExist(employeeRequest.getEmail()), isIdentityNumberExist(employeeRequest.getIdentityNumber()),
//				isPasswordAndConfirmPasswordEqual(employeeRequest.getPassword(), employeeRequest.getConfirmPassword()));
		var result = BusinessRules.run(isRequiredFieldsEmpty(employeeRequest), isPersonReal(employeeRequest),
				isEmailExist(employeeRequest.getEmail()),isPasswordAndConfirmPasswordEqual(employeeRequest.getPassword(), employeeRequest.getConfirmPassword()),
				isIdentityNumberExist(employeeRequest.getIdentityNumber())
				);
//		if (result != null) {
//			return new ErrorResult(result.getMessage());
//		}
		Employee employee = mapperService.forRequest().map(employeeRequest, Employee.class);
		employee.setPassword(passwordEncoder.encode(employee.getPassword()));
		employeeRepository.save(employee);
		return new SuccessResult(Message.employeeAdded);
	}

	@Override
	public DataResult<List<GetAllEmployeeResponse>> getAll() {
		List<GetAllEmployeeResponse> result = this.employeeRepository.findAll().stream().map(
				employee->this.mapperService.forResponse().map(employee, GetAllEmployeeResponse.class)).collect(Collectors.toList());
				
		return new SuccessDataResult<List<GetAllEmployeeResponse>>(result);
	}

	@Override
	public DataResult<GetEmployeeResponse> getById(int id) {
		GetEmployeeResponse getEmployeeResponse = this.mapperService.forResponse().map(employeeRepository.getReferenceById(id), GetEmployeeResponse.class);
//		Employee employee = employeeRepository.getReferenceById(id);
//		GetEmployeeResponse getEmployeeResponse = new GetEmployeeResponse();
//		getEmployeeResponse.setId(employee.getId());
//		getEmployeeResponse.setBirthYear(employee.getBirthYear());
//		getEmployeeResponse.setEmail(employee.getEmail());
//		getEmployeeResponse.setFirstName(employee.getFirstName());
//		getEmployeeResponse.setLastName(employee.getLastName());
//		getEmployeeResponse.setPassword(employee.getPassword());
//		getEmployeeResponse.setIdentityNumber(employee.getIdentityNumber());
		return new SuccessDataResult<GetEmployeeResponse>(getEmployeeResponse);
	}

	@Override
	public Result update(UpdateEmployeeRequest employeeRequest) {
		Employee employee = employeeRepository.getReferenceById(employeeRequest.getId());

//		Employee employee = modelMapper.map(getById(employeeRequest.getId()).getData(), Employee.class); 
//		employee.setVerified(employeeRequest.isVerified()); 
		employeeRepository.save(employee);
		return new SuccessResult();
	}
	@Override
	public Result delete(int id) {
		Employee employee = employeeRepository.getReferenceById(id);
		employeeRepository.delete(employee);
		return new SuccessResult(Message.employeeDeleted);
	}


	// business rules

	private Result isRequiredFieldsEmpty(CreateEmployeeRequest employeeRequest) {
		if (employeeRequest.getFirstName().isBlank() && employeeRequest.getLastName().isBlank()
				&& employeeRequest.getIdentityNumber().isBlank() && employeeRequest.getBirthYear() == 0
				&& employeeRequest.getPassword().isBlank() && employeeRequest.getConfirmPassword().isBlank()) {
			return new ErrorResult(Message.requiredFieldEmpty);
		}
		return new SuccessResult();
	}

	private Result isPersonReal(CreateEmployeeRequest employeeRequest) {
		if (userCheckService.checkPerson(employeeRequest.getFirstName(), employeeRequest.getLastName(),
				employeeRequest.getIdentityNumber(), employeeRequest.getBirthYear()).isSuccess()) {
			return new SuccessResult();
		}
		return new ErrorResult(Message.personIsNotReal);
	}

	private Result isEmailExist(String email) {
		if (employeeRepository.findByEmailEquals(email).isEmpty()) {
			return new SuccessResult();
		}
		return new ErrorResult(Message.emailAlreadyExist);
	}

	private Result isIdentityNumberExist(String identityNumber) {
		if (employeeRepository.findByIdentityNumberEquals(identityNumber).isEmpty()) {
			return new SuccessResult();
		}
		return new ErrorResult(Message.identityNumberAlreadyExist);
	}

	private Result isPasswordAndConfirmPasswordEqual(String password, String confirmPassword) {
		if (password.equals(confirmPassword)) {
			return new SuccessResult();
		}
		return new ErrorResult(Message.passwordAndConfirmPasswordNotMatched);
	}

	@Override
	public DataResult<GetEmployeeResponse> getEmployeeDetails(int employeeId) {
		return new SuccessDataResult<GetEmployeeResponse>(this.employeeRepository.getEmployeeDetails(employeeId));
	}

	@Override
	public Employee getEmployee(int id) {
		
		return this.employeeRepository.getReferenceById(id);
	}

}
