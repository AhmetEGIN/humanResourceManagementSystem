package kodlamaio.hrms.webApi.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import kodlamaio.hrms.business.abstracts.PositionService;
import kodlamaio.hrms.business.requests.positionRequests.CreatePositionRequest;
import kodlamaio.hrms.business.responses.positionResponses.GetAllPositionResponse;
import kodlamaio.hrms.business.responses.positionResponses.GetPositionResponse;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;

@RestController
@RequestMapping("/api/position")
public class PositionsController {

	private PositionService positionService;

	@Autowired
	public PositionsController(PositionService positionService) {
		this.positionService = positionService;
	}

	@GetMapping("/getall")
	public DataResult<List<GetAllPositionResponse>> getAll() {
		return positionService.getAll();
	}

	@PostMapping("/add")
	public Result add(@RequestBody CreatePositionRequest positionRequest) {
		return positionService.add(positionRequest);
	}

	@GetMapping("/getById")
	public DataResult<GetPositionResponse> getPositionById(@RequestParam int id) {
		return this.positionService.getPositionById(id);
	}

}
