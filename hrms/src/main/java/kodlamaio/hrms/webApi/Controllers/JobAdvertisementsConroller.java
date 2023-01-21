package kodlamaio.hrms.webApi.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.hrms.business.abstracts.JobAdvertisementService;
import kodlamaio.hrms.business.requests.jobAdvertisementRequests.CreateJobAdvertisementRequest;
import kodlamaio.hrms.business.responses.jobAdvertisementResponses.GetAllJobAdvertisementResponse;
import kodlamaio.hrms.business.responses.jobAdvertisementResponses.GetJobAdvertisementResponse;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;

@RestController
@RequestMapping("/api/jobadvertisement")
@CrossOrigin
public class JobAdvertisementsConroller {
	private JobAdvertisementService advertisementService;
	@Autowired
	public JobAdvertisementsConroller(JobAdvertisementService advertisementService) {
		this.advertisementService = advertisementService;
	}
	
	@PostMapping("/add")
	public Result add(@RequestBody CreateJobAdvertisementRequest jobAdvertisement) {
		var result = this.advertisementService.add(jobAdvertisement);
		return result;
	}
	
	@GetMapping("/getbyid")
	public DataResult<GetJobAdvertisementResponse> getById(@RequestParam int id){
		return this.advertisementService.getById(id);
	}
	
	@GetMapping("getactivejobadvertisements")
	public DataResult<List<GetAllJobAdvertisementResponse>> getActiveJobAdvertisements(){
		return this.advertisementService.getAllActiveJobAdvertisements();
	}
	
	@GetMapping("/getJobAdvertisemenstByEmployer")
	public DataResult<List<GetAllJobAdvertisementResponse>> getJobAdvertisementsByEmployerId(@RequestParam int employerId){
		return this.advertisementService.getAllJobAdvertisementsByEmployer(employerId);
	}
	
	@PostMapping("/changeStateOfAdvertisement")
	public Result toggleStateOfActive(int jobId, boolean state) {
		return this.advertisementService.toggleStateOfActive(jobId, state);
		
	}
	
	@GetMapping("/getAllActiveJobAdvertisementsPageable")
	public DataResult<List<GetAllJobAdvertisementResponse>> getAllActiveJobAdvertisements(@RequestParam int pageNo,int pageSize){
		return this.advertisementService.getAllActiveJobAdvertisements(pageNo, pageSize);
	}
	
	@GetMapping("/getAllActiveJobAdvertisementsSortedByDeadline")
	public DataResult<List<GetAllJobAdvertisementResponse>> getAllActiveJobAdvertisementsSortedByDeadline(@RequestParam int sort){
		return this.advertisementService.getAllActiveJobAdvertisementsSortedByDeadline(sort);
	}
}
