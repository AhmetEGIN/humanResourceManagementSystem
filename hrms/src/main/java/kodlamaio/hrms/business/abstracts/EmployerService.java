package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.business.requests.employerRequests.CreateEmployerRequest;
import kodlamaio.hrms.business.requests.employerRequests.UpdateEmployerRequest;
import kodlamaio.hrms.business.responses.employerResponses.GetAllEmployerResponse;
import kodlamaio.hrms.business.responses.employerResponses.GetEmployerResponse;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;

public interface EmployerService {
	Result add(CreateEmployerRequest employerRequest);
	Result update(UpdateEmployerRequest employerRequest);
	Result setVerifyFromAdmin(int employerId);
	DataResult<List<GetAllEmployerResponse>> getAll();
	DataResult<GetEmployerResponse> getById(int id);
}
