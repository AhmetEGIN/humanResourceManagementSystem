package kodlamaio.hrms.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.EmployeeService;
import kodlamaio.hrms.business.abstracts.UserService;
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
	private UserService userService;
	@Autowired
	public EmployeeManager(EmployeeRepository employeeRepository, UserCheckService userCheckService,
			ModelMapperService mapperService, UserService userService) {
		this.employeeRepository = employeeRepository;
		this.userCheckService = userCheckService;
		this.mapperService = mapperService;
		this.userService = userService;
		
	}

	@Override
	public Result add(CreateEmployeeRequest employeeRequest) {
//		List<Result> rules = new ArrayList<Result>();
//		Collections.addAll(isRequiredFieldsEmpty(employeeRequest), isPersonReal(employeeRequest.getFirstName(), employeeRequest.getLastName(), employeeRequest.getIdentityNumber(), employeeRequest.getBirthYear()),
//				isEmailExist(employeeRequest.getEmail()), isIdentityNumberExist(employeeRequest.getIdentityNumber())
//				);
		var result = BusinessRules.run(
				isRequiredFieldsEmpty(employeeRequest), 
				isPersonReal(employeeRequest.getFirstName(),employeeRequest.getLastName().toString().trim(),employeeRequest.getIdentityNumber(), employeeRequest.getBirthYear()),
				isIdentityNumberExist(employeeRequest.getIdentityNumber())
				);
		if (result != null) {
			return new ErrorResult(result.getMessage());
		}
		Employee employee = mapperService.forRequest().map(employeeRequest, Employee.class);
		employeeRepository.save(employee);
		return new SuccessResult(Message.EMPLOYEE_ADDED);
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
		return new SuccessDataResult<GetEmployeeResponse>(getEmployeeResponse);
	}

	@Override
	public Result update(UpdateEmployeeRequest employeeRequest) {
		Employee employee = employeeRepository.getReferenceById(employeeRequest.getId());
		updateEmployee(employee, employeeRequest);

		var result = BusinessRules.run(
				isPersonReal(employeeRequest.getFirstName(),employeeRequest.getLastName(),employeeRequest.getIdentityNumber(), employeeRequest.getBirthYear()),
				isEmailExist(employeeRequest.getEmail()),
				isIdentityNumberExist(employeeRequest.getIdentityNumber())
				);
		if (result != null) {
			return new ErrorResult(result.getMessage());
		}
		
		employeeRepository.save(employee);
		return new SuccessResult(Message.USER_UPDATED);
	}
	@Override
	public Result delete(int id) {
		Employee employee = employeeRepository.getReferenceById(id);
		employeeRepository.delete(employee);
		return new SuccessResult(Message.EMPLOYEE_DELETED);
	}
	@Override
	public DataResult<GetEmployeeResponse> getEmployeeDetails(int employeeId) {
		return new SuccessDataResult<GetEmployeeResponse>(this.employeeRepository.getEmployeeDetails(employeeId));
	}


	// business rules

	private Result isRequiredFieldsEmpty(CreateEmployeeRequest employeeRequest) {
		if (employeeRequest.getFirstName().isBlank() && employeeRequest.getLastName().isBlank()
				&& employeeRequest.getIdentityNumber().isBlank() && employeeRequest.getBirthYear() == 0
				&& employeeRequest.getPassword().isBlank() && employeeRequest.getConfirmPassword().isBlank()) {
			return new ErrorResult(Message.REQUIRED_FIELD_EMPTY);
		}
		return new SuccessResult();
	}

	private Result isPersonReal(String firstName, String lastName, String identityNumber, short birthYear) {
		if (userCheckService.checkPerson(firstName, lastName, identityNumber, birthYear).isSuccess()) {
			return new SuccessResult();	
		}
		return new ErrorResult(Message.PERSON_IS_NOT_REAL);
	}

	private Result isEmailExist(String email) {
		if (userService.isEmailExists(email).isSuccess()) {
			return new SuccessResult();
		}
		
		return new ErrorResult(Message.EMAIL_ALREADY_EXISTS);
	}

	private Result isIdentityNumberExist(String identityNumber) {
		if (!employeeRepository.existsEmployeeByIdentityNumberEquals(identityNumber)) {
			return new SuccessResult();
		}
		return new ErrorResult(Message.IDENTITY_NUMBER_ALREADY_EXIST);
	}
	
	private Result updateEmployee(Employee employee, UpdateEmployeeRequest employeeRequest) {
		employee.setFirstName(employeeRequest.getFirstName());
		employee.setLastName(employeeRequest.getLastName());
		employee.setIdentityNumber(employeeRequest.getIdentityNumber());
		employee.setBirthYear(employeeRequest.getBirthYear());
		employee.setEmail(employeeRequest.getEmail());
		employee.setPassword(employeeRequest.getPassword());
		employee.setEmailVerified(employeeRequest.isVerified());
		
		return new SuccessResult();
	}






}
