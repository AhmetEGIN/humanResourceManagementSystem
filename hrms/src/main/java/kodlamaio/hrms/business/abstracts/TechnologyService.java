package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.business.requests.technologyRequests.CreateTechnologyRequest;
import kodlamaio.hrms.business.requests.technologyRequests.UpdateTechnologyRequest;
import kodlamaio.hrms.business.responses.technologyResponses.GetAllTechnologyResponse;
import kodlamaio.hrms.business.responses.technologyResponses.GetTechnologyResponse;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;

public interface TechnologyService {
	Result add(CreateTechnologyRequest technologyRequest);
	Result update(int id, UpdateTechnologyRequest technologyRequest);
	DataResult<List<GetAllTechnologyResponse>> getAll();
	DataResult<GetTechnologyResponse> getById(int id);
	Result deleteById(int id);
}
