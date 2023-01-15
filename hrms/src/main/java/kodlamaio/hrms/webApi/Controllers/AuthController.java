package kodlamaio.hrms.webApi.Controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import kodlamaio.hrms.business.abstracts.AuthService;
import kodlamaio.hrms.business.abstracts.UserService;
import kodlamaio.hrms.business.requests.employeeRequests.CreateEmployeeRequest;
import kodlamaio.hrms.business.requests.employerRequests.CreateEmployerRequest;
import kodlamaio.hrms.business.requests.hrmsAdminRequests.CreateHrmsAdminRequest;
import kodlamaio.hrms.business.responses.authResponses.AuthenticationResponse;
import kodlamaio.hrms.core.entities.User;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;

import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.core.utilities.results.ErrorDataResult;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	private AuthService  authService;
	private UserService userService;

	@Autowired
	public AuthController(AuthService authService, UserService userService) {
		this.authService = authService;
		this.userService = userService;
	}
	
	@PostMapping("/registeremployee")
	public DataResult<AuthenticationResponse> registerEmployee(@Valid @RequestBody CreateEmployeeRequest employeeRequest) {
		var result = authService.registerEmployee(employeeRequest);
		return result;
	}
	
	@PostMapping("/registeremployer")
	public Result registerEmployer(@RequestBody CreateEmployerRequest employerRequest) {
		var result = authService.registerEmployer(employerRequest);
		if (result.isSuccess()) {
			return new SuccessResult(result.getMessage());
		}
		return new ErrorResult(result.getMessage());
	}
	
	@PostMapping("/registerhrmsadmin")
	public Result registerHrmsAdmin(CreateHrmsAdminRequest hrmsAdminRequest) {
		return this.authService.registerHrmsAdmin(hrmsAdminRequest);
	}
	
	
	@PostMapping("/verifyfromhrms")
	public Result verifyFromHrms(int userId) {
		var result = authService.verifyEmployer(userId);
		if (result.isSuccess()) {
			return new SuccessResult(result.getMessage());
		}
		return new ErrorResult(result.getMessage());
	}
	
	@PostMapping("/verify")
	public Result verifyEmail(int userId) {
		var result = authService.verifyEmail(userId);
		if (result.isSuccess()) {
			return new SuccessResult();
		}
		return new ErrorResult();
	}
	
	@GetMapping("/getUser")
	public DataResult<User> getUser(@RequestParam int userId){
		var result = this.userService.getUser(userId);
		return new SuccessDataResult<User>(result.getData());
	}
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorDataResult<Object> handleValidationException(MethodArgumentNotValidException exceptions){
		Map<String, String> validationErrors = new HashMap<String ,String>();
		for(FieldError fieldError : exceptions.getBindingResult().getFieldErrors() ) {
			validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
		}
		ErrorDataResult<Object> errors = new ErrorDataResult<Object>(validationErrors, "Doğrulama hataları");
		return errors;
	}
	
}
