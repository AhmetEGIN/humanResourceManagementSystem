package kodlamaio.hrms.webApi.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.hrms.business.abstracts.LinkedinAccountService;
import kodlamaio.hrms.business.requests.LinkedinAccountRequests.CreateLinkedinAccountRequest;
import kodlamaio.hrms.business.requests.LinkedinAccountRequests.UpdateLinkedinAccountRequest;
import kodlamaio.hrms.business.responses.linkedinAccountResponses.GetAllLinkedinAccountResponse;
import kodlamaio.hrms.business.responses.linkedinAccountResponses.GetLinkedinAccountResponse;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;

@RestController
@RequestMapping("api/linkedinAccount")
public class LinkedinAccountsController {
	private LinkedinAccountService linkedinAccountService;

	@Autowired
	public LinkedinAccountsController(LinkedinAccountService linkedinAccountService) {
		this.linkedinAccountService = linkedinAccountService;
	}

	@PostMapping("/add")
	public Result add(@RequestBody CreateLinkedinAccountRequest linkedinAccountRequest) {
		return this.linkedinAccountService.add(linkedinAccountRequest);
	}

	@PostMapping("/update")
	public Result Update(@RequestParam int id, @RequestBody UpdateLinkedinAccountRequest linkedinAccountRequest) {
		return this.linkedinAccountService.update(id, linkedinAccountRequest);
	}

	@GetMapping("/getAll")
	public DataResult<List<GetAllLinkedinAccountResponse>> getAll(){
		return this.linkedinAccountService.getAll();
	}
	
	@GetMapping("/getById")
	public DataResult<GetLinkedinAccountResponse> getById(@RequestParam int id){
		return this.linkedinAccountService.getById(id);
	}
}
