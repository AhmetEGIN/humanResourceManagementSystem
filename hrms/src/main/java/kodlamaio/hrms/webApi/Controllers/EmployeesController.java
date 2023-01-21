package kodlamaio.hrms.webApi.Controllers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.RequestBody;
import kodlamaio.hrms.business.abstracts.EmployeeService;
import kodlamaio.hrms.business.requests.employeeRequests.UpdateEmployeeRequest;
import kodlamaio.hrms.business.responses.employeeResponses.GetAllEmployeeResponse;
import kodlamaio.hrms.business.responses.employeeResponses.GetEmployeeResponse;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorDataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;

@RestController
@RequestMapping("/api/employee")
@CrossOrigin
public class EmployeesController {
	private EmployeeService employeeService;

	public EmployeesController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@GetMapping("/getbyid")
	public DataResult<GetEmployeeResponse> getById(int id) {
		var result = employeeService.getById(id);
		if (result.isSuccess()) {
			return new SuccessDataResult<GetEmployeeResponse>(result.getData());
		}
		return new ErrorDataResult<>(result.getMessage());
	}
	
	@GetMapping("/getall")
	public DataResult<List<GetAllEmployeeResponse>> getAll(){
		return employeeService.getAll();
	}
	
	
	@PostMapping("/delete")
	public Result delete(int id) {
		var result = employeeService.delete(id);
		if (result.isSuccess()) {
			return new SuccessResult(result.getMessage());
		}
		return new ErrorResult(result.getMessage());
	}
	
	@GetMapping("/getEmployeeDetails")
	public DataResult<GetEmployeeResponse> getEmployeeDetails(@RequestParam int employeeId){
		return this.employeeService.getEmployeeDetails(employeeId);
	}
	
	@PostMapping("/updateemployee")
	public Result update(@RequestBody UpdateEmployeeRequest employeeRequest) {
		return this.employeeService.update(employeeRequest);
	}
	
	
	
	
}
