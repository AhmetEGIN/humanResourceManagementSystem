package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.business.requests.cvRequests.CreateCvRequest;
import kodlamaio.hrms.business.responses.cvResponses.GetAllCvResponse;
import kodlamaio.hrms.business.responses.cvResponses.GetCvResponse;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.Cv;

public interface CvService {
	
	Result add(CreateCvRequest cvRequest);
	DataResult<List<GetAllCvResponse>> getAll();
	DataResult<GetCvResponse> getById(int id);
	Cv getCv(int id);
	
}
