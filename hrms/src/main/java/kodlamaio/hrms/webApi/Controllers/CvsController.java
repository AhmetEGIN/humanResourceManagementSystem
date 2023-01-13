package kodlamaio.hrms.webApi.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import kodlamaio.hrms.business.abstracts.CvService;
import kodlamaio.hrms.business.requests.cvRequests.CreateCvRequest;

import kodlamaio.hrms.business.responses.cvResponses.GetAllCvResponse;
import kodlamaio.hrms.business.responses.cvResponses.GetCvResponse;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;

@RestController
@RequestMapping("/api/cv")
public class CvsController {
	
	private CvService cvService;

	@Autowired
	public CvsController(CvService cvService) {
		this.cvService = cvService;
	}
	
	
	@PostMapping("/add")
	public Result add(@RequestBody CreateCvRequest cvRequest) {
		var result = this.cvService.add(cvRequest);
		return result;
	}
	
	
	@GetMapping("/getAll")
	public DataResult<List<GetAllCvResponse>> getAll(){
		return this.cvService.getAll();
	}
	
	@GetMapping("/getById")
	public DataResult<GetCvResponse> getById(@RequestParam int id){
		return this.cvService.getById(id);
	}
	
	
	
}

