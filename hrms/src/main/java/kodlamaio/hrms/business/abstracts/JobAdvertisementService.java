package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.business.requests.jobAdvertisementRequests.CreateJobAdvertisementRequest;
import kodlamaio.hrms.business.responses.jobAdvertisementResponses.GetAllJobAdvertisementResponse;
import kodlamaio.hrms.business.responses.jobAdvertisementResponses.GetJobAdvertisementResponse;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;

public interface JobAdvertisementService {
	
	Result add(CreateJobAdvertisementRequest jobAdvertisement);
	DataResult<GetJobAdvertisementResponse> getById(int id);
	DataResult<List<GetAllJobAdvertisementResponse>> getAllActiveJobAdvertisements();
	DataResult<List<GetAllJobAdvertisementResponse>> getAllActiveJobAdvertisements(int pageNo,int pageSize);
	DataResult<List<GetAllJobAdvertisementResponse>> getAllActiveJobAdvertisementsSortedByDeadline(int sort);
	DataResult<List<GetAllJobAdvertisementResponse>> getAllJobAdvertisements();
	DataResult<List<GetAllJobAdvertisementResponse>> getAllJobAdvertisementsByEmployer(int employerId);
	Result toggleStateOfActive(int jobId,boolean state);
}
