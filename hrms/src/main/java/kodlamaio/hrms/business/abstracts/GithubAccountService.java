package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.business.requests.githubAccountRequests.CreateGithubAccountRequest;
import kodlamaio.hrms.business.requests.githubAccountRequests.UpdateGithubAccountRequest;
import kodlamaio.hrms.business.responses.githubAccountResponses.GetAllGithubAccountResponse;
import kodlamaio.hrms.business.responses.githubAccountResponses.GetGithubAccountResponse;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;

public interface GithubAccountService {
	Result add(CreateGithubAccountRequest githubAccountRequest);
	Result update(int id, UpdateGithubAccountRequest githubRequest);
	DataResult<List<GetAllGithubAccountResponse>> getAll();
	DataResult<GetGithubAccountResponse> getById(int id);
}
