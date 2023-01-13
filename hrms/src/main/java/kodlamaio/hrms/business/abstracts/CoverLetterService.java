package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.business.requests.coverLetterRequests.CreateCoverLetterRequest;
import kodlamaio.hrms.business.requests.coverLetterRequests.UpdateCoverLetterRequest;
import kodlamaio.hrms.business.responses.coverLetterResponses.GetAllCoverLetterResponse;
import kodlamaio.hrms.business.responses.coverLetterResponses.GetCoverLetterResponse;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;

public interface CoverLetterService {
	Result add(CreateCoverLetterRequest coverLetterRequest);
	Result update(int id, UpdateCoverLetterRequest coverLetterRequest);
	DataResult<List<GetAllCoverLetterResponse>> getAll();
	DataResult<GetCoverLetterResponse> getById(int id);
}
