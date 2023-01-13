package kodlamaio.hrms.webApi.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import kodlamaio.hrms.business.abstracts.LanguageService;
import kodlamaio.hrms.business.requests.languageRequests.CreateLanguageRequest;
import kodlamaio.hrms.business.responses.languageResponses.GetAllLanguageResponse;
import kodlamaio.hrms.business.responses.languageResponses.GetLanguageResponse;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;

@RestController
@RequestMapping("/api/language")
public class LanguagesControllers {
	private LanguageService languageService;

	@Autowired
	public LanguagesControllers(LanguageService languageService) {
		this.languageService = languageService;
	}

	@PostMapping("/add")
	public Result add(@RequestBody CreateLanguageRequest languageRequest) {
		return this.languageService.add(languageRequest);
	}

	@GetMapping("/getAll")
	public DataResult<List<GetAllLanguageResponse>> getAll() {
		return this.languageService.getAll();
	}

	@GetMapping("/id")
	public DataResult<GetLanguageResponse> getById(@RequestParam int id){
		return this.languageService.getById(id);
	}

}
