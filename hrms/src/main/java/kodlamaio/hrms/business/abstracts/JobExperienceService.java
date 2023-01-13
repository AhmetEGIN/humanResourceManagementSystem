package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.business.requests.jobExperienceRequests.CreateJobExperienceRequest;
import kodlamaio.hrms.business.responses.jobExperienceResponses.GetAllJobExperienceResponse;
import kodlamaio.hrms.business.responses.jobExperienceResponses.GetJobExperienceResponse;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;

public interface JobExperienceService {
	Result add(CreateJobExperienceRequest jobExprienceRequest);
	DataResult<List<GetAllJobExperienceResponse>> getAll();
	DataResult<GetJobExperienceResponse> getById(int id);
	DataResult<List<GetJobExperienceResponse>> getJobExperiencesByCvIdSortedByYear(int sortType, int cvId);
}
