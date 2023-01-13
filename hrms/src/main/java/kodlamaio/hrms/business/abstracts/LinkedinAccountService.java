package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.business.requests.LinkedinAccountRequests.CreateLinkedinAccountRequest;
import kodlamaio.hrms.business.requests.LinkedinAccountRequests.UpdateLinkedinAccountRequest;
import kodlamaio.hrms.business.responses.linkedinAccountResponses.GetAllLinkedinAccountResponse;
import kodlamaio.hrms.business.responses.linkedinAccountResponses.GetLinkedinAccountResponse;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;

public interface LinkedinAccountService {
	Result add(CreateLinkedinAccountRequest linkedinAccountRequest);
	Result update(int id, UpdateLinkedinAccountRequest linkedinAccountRequest);
	DataResult<List<GetAllLinkedinAccountResponse>> getAll();
	DataResult<GetLinkedinAccountResponse> getById(int id);
}
