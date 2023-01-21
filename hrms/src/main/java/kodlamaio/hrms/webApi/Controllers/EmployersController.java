package kodlamaio.hrms.webApi.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.hrms.business.abstracts.EmployerService;
import kodlamaio.hrms.business.requests.employerRequests.CreateEmployerRequest;
import kodlamaio.hrms.business.responses.employerResponses.GetAllEmployerResponse;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorDataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;

@RestController
@RequestMapping("/api/employer")
@CrossOrigin
public class EmployersController {
	private EmployerService employerService;
	@Autowired
	public EmployersController(EmployerService employerService) {
		this.employerService = employerService;
	}
	
	@GetMapping("/getall")
	public DataResult<List<GetAllEmployerResponse>> getAll(){
		var result = employerService.getAll();
		if (result.isSuccess()) {
			return new SuccessDataResult<List<GetAllEmployerResponse>>(result.getData(), result.getMessage());
		}
		return new ErrorDataResult<>(result.getMessage());
	}
	
	@PostMapping("/add")
	public Result add(CreateEmployerRequest employerRequest) {
		var result = this.employerService.add(employerRequest);
		if (result.isSuccess()) {
			return result;
		}
		return result;
	}
	
}
