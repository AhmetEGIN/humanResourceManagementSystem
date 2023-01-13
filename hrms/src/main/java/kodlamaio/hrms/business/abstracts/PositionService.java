package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.business.requests.positionRequests.CreatePositionRequest;
import kodlamaio.hrms.business.responses.positionResponses.GetAllPositionResponse;
import kodlamaio.hrms.business.responses.positionResponses.GetPositionResponse;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;

public interface PositionService {
	DataResult<List<GetAllPositionResponse>> getAll();
	DataResult<GetPositionResponse> getPositionById(int id);
	Result add(CreatePositionRequest positionRequest);
}
