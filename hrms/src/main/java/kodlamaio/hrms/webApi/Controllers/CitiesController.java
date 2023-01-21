package kodlamaio.hrms.webApi.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.hrms.business.abstracts.CityService;
import kodlamaio.hrms.business.requests.cityRequests.CreateCityRequest;
import kodlamaio.hrms.business.responses.cityResponses.GetCityResponse;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;

@RestController
@RequestMapping("/api/city")
@CrossOrigin
public class CitiesController {
	private CityService cityService;
	
	@Autowired
	public CitiesController(CityService cityService) {
		this.cityService = cityService;
	}
	
	@PostMapping("/add")
	public Result add(@RequestBody CreateCityRequest cityRequest) {
		return this.cityService.add(cityRequest);
	}
	
	@GetMapping("/getById")
	DataResult<GetCityResponse> getById(@RequestParam int id){
		return this.cityService.getById(id);
	}
	
	
}
