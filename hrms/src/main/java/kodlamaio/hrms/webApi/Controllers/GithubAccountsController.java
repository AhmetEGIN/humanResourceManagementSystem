package kodlamaio.hrms.webApi.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.hrms.business.abstracts.GithubAccountService;
import kodlamaio.hrms.business.requests.githubAccountRequests.CreateGithubAccountRequest;
import kodlamaio.hrms.business.requests.githubAccountRequests.UpdateGithubAccountRequest;
import kodlamaio.hrms.business.responses.githubAccountResponses.GetAllGithubAccountResponse;
import kodlamaio.hrms.business.responses.githubAccountResponses.GetGithubAccountResponse;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;

@RestController
@RequestMapping("/api/githubAccount")
public class GithubAccountsController {
	private GithubAccountService githubAccountService;

	@Autowired
	public GithubAccountsController(GithubAccountService githubAccountService) {
		this.githubAccountService = githubAccountService;
	}

	@PostMapping("/add")
	public Result add(@RequestBody CreateGithubAccountRequest githubAccountRequest) {
		return this.githubAccountService.add(githubAccountRequest);
	}

	@PostMapping("/update")
	public Result Update(@RequestParam int id, @RequestBody UpdateGithubAccountRequest githubAccountRequest) {
		return this.githubAccountService.update(id, githubAccountRequest);
	}

	@GetMapping("/getAll")
	public DataResult<List<GetAllGithubAccountResponse>> getAll(){
		return this.githubAccountService.getAll();
	}
	
	@GetMapping("/getById")
	public DataResult<GetGithubAccountResponse> getById(@RequestParam int id){
		return this.githubAccountService.getById(id);
	}
}
