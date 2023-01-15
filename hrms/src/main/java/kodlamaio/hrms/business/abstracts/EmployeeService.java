package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.business.requests.employeeRequests.CreateEmployeeRequest;
import kodlamaio.hrms.business.requests.employeeRequests.UpdateEmployeeRequest;
import kodlamaio.hrms.business.responses.employeeResponses.GetAllEmployeeResponse;
import kodlamaio.hrms.business.responses.employeeResponses.GetEmployeeResponse;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;

public interface EmployeeService {
	
	Result add(CreateEmployeeRequest employeeRequest);
	Result update(UpdateEmployeeRequest employeeRequest);
	Result delete(int id);
	DataResult<GetEmployeeResponse> getById(int id);
	DataResult<List<GetAllEmployeeResponse>> getAll();
	DataResult<GetEmployeeResponse> getEmployeeDetails(int employeeId);
}
