package kodlamaio.hrms.webApi.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.hrms.business.abstracts.CoverLetterService;
import kodlamaio.hrms.business.requests.coverLetterRequests.CreateCoverLetterRequest;
import kodlamaio.hrms.business.requests.coverLetterRequests.UpdateCoverLetterRequest;
import kodlamaio.hrms.business.responses.coverLetterResponses.GetAllCoverLetterResponse;
import kodlamaio.hrms.business.responses.coverLetterResponses.GetCoverLetterResponse;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;

@RestController
@RequestMapping("/api/coverLetter")
public class CoverLettersController {
	private CoverLetterService coverLetterService;

	@Autowired
	public CoverLettersController(CoverLetterService coverLetterService) {
		this.coverLetterService = coverLetterService;
	}

	@PostMapping("/add")
	public Result add(@RequestBody CreateCoverLetterRequest coverLetterRequest) {
		return this.coverLetterService.add(coverLetterRequest);
	}

	@PostMapping("/update")
	public Result Update(@RequestParam int id, @RequestBody UpdateCoverLetterRequest coverLetterRequest) {
		return this.coverLetterService.update(id, coverLetterRequest);
	}

	@GetMapping("/getAll")
	public DataResult<List<GetAllCoverLetterResponse>> getAll(){
		return this.coverLetterService.getAll();
	}
	
	@GetMapping("/getById")
	public DataResult<GetCoverLetterResponse> getById(@RequestParam int id){
		return this.coverLetterService.getById(id);
	}
}
