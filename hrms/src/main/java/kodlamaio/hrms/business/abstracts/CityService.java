package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.business.requests.cityRequests.CreateCityRequest;
import kodlamaio.hrms.business.responses.cityResponses.GetCityResponse;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;

public interface CityService {
	Result add(CreateCityRequest cityRequest);
	DataResult<GetCityResponse> getById(int id);

}
