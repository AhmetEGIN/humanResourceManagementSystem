package kodlamaio.hrms.webApi.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.hrms.business.abstracts.TechnologyService;
import kodlamaio.hrms.business.requests.technologyRequests.UpdateTechnologyRequest;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;

@RestController
@RequestMapping("/api/technology")
public class TechnologiesController {
	private TechnologyService technologyService;

	@Autowired
	public TechnologiesController(TechnologyService technologyService) {
		this.technologyService = technologyService;
	}

	@PostMapping("/add")
	public Result add(@RequestBody kodlamaio.hrms.business.requests.technologyRequests.CreateTechnologyRequest TechnologyRequest) {
		return this.technologyService.add(TechnologyRequest);
	}

	@PostMapping("/update")
	public Result Update(@RequestParam int id, @RequestBody UpdateTechnologyRequest TechnologyRequest) {
		return this.technologyService.update(id, TechnologyRequest);
	}

	@GetMapping("/getAll")
	public DataResult<List<kodlamaio.hrms.business.responses.technologyResponses.GetAllTechnologyResponse>> getAll(){
		return this.technologyService.getAll();
	}
	
	@GetMapping("/getById")
	public DataResult<kodlamaio.hrms.business.responses.technologyResponses.GetTechnologyResponse> getById(@RequestParam int id){
		return this.technologyService.getById(id);
	}
	
	@GetMapping("/delete")
	public Result deleteById(@RequestParam int id) {
		return this.technologyService.deleteById(id);
	}
	
}
