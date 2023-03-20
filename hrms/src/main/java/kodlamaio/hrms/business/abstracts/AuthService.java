package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.business.requests.authRequests.LoginRequest;
import kodlamaio.hrms.business.requests.employeeRequests.CreateEmployeeRequest;
import kodlamaio.hrms.business.requests.employerRequests.CreateEmployerRequest;
import kodlamaio.hrms.business.requests.hrmsAdminRequests.CreateHrmsAdminRequest;
import kodlamaio.hrms.business.responses.authResponses.AuthenticationResponse;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;

public interface AuthService {
	
	DataResult<AuthenticationResponse> registerEmployee(CreateEmployeeRequest employeeRequest);
	DataResult<AuthenticationResponse> registerEmployer(CreateEmployerRequest employerRequest);
	DataResult<AuthenticationResponse> registerHrmsAdmin(CreateHrmsAdminRequest hrmsAdminRequest);
	DataResult<AuthenticationResponse> login(LoginRequest loginRequest);

	Result verifyEmail(int userId);
	Result verifyEmployer(int userId);
}
