package kodlamaio.hrms.webApi.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.hrms.business.abstracts.JobExperienceService;
import kodlamaio.hrms.business.requests.jobExperienceRequests.CreateJobExperienceRequest;
import kodlamaio.hrms.business.responses.jobExperienceResponses.GetAllJobExperienceResponse;
import kodlamaio.hrms.business.responses.jobExperienceResponses.GetJobExperienceResponse;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;

@RestController
@RequestMapping("/api/jobExperience")
public class JobExperiencesController {

	private JobExperienceService experienceService;

	@Autowired
	public JobExperiencesController(JobExperienceService experienceService) {
		this.experienceService = experienceService;
	}

	@PostMapping("/add")
	public Result add(@RequestBody CreateJobExperienceRequest jobExprienceRequest) {
		return this.experienceService.add(jobExprienceRequest);
	}

	@GetMapping("/getAll")
	public DataResult<List<GetAllJobExperienceResponse>> getAll() {
		return this.experienceService.getAll();

	}
	@GetMapping("/getById")
	public DataResult<GetJobExperienceResponse> getById(@RequestParam int id){
		return this.experienceService.getById(id);
	}
	
	@GetMapping("/getJobExperiencesByCvIdSortedByReleaseDate")
	public DataResult<List<GetJobExperienceResponse>> getJobExperiencesByCvIdSortedByYear(int sortType, int cvId){
		return this.experienceService.getJobExperiencesByCvIdSortedByYear(sortType, cvId);
	}
}
