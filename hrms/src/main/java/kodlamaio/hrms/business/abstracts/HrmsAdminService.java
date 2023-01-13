package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.business.requests.hrmsAdminRequests.CreateHrmsAdminRequest;
import kodlamaio.hrms.core.utilities.results.Result;

public interface HrmsAdminService {
	Result add(CreateHrmsAdminRequest hrmsAdminRequest);
	Result verifyEmployer(int employerId);
}
