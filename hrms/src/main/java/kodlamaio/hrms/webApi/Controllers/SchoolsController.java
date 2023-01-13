package kodlamaio.hrms.webApi.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.hrms.business.abstracts.SchoolService;
import kodlamaio.hrms.business.requests.schoolRequests.CreateSchoolRequest;
import kodlamaio.hrms.business.responses.schoolResonses.GetAllSchoolResponse;
import kodlamaio.hrms.business.responses.schoolResonses.GetSchoolResponse;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;

@RestController
@RequestMapping("/api/school")
public class SchoolsController {
	private SchoolService schoolService;
	
	@Autowired
	public SchoolsController(SchoolService schoolService) {
		this.schoolService = schoolService;
	}
	
	@PostMapping("/add")
	public Result add(@RequestBody CreateSchoolRequest schoolRequest) {
		return this.schoolService.add(schoolRequest);
	}
	
	@GetMapping("/getAll")
	public DataResult<List<GetAllSchoolResponse>> getAll(){
		return this.schoolService.getAll();
	}
	
	@GetMapping("/getById")
	public DataResult<GetSchoolResponse> getById(@RequestParam int id){
		return this.schoolService.getById(id);
	}
	
	@GetMapping("/getUsersSortedByGraduationDate")
	public DataResult<List<GetSchoolResponse>> getUserByCvIdSortedByGraduationDate(int userId, int sortType){
		return this.schoolService.getUserByCvIdSortedByGraduationDate(userId, sortType);
	}
	
	
}
