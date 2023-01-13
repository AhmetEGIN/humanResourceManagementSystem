package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.business.requests.schoolRequests.CreateSchoolRequest;
import kodlamaio.hrms.business.responses.schoolResonses.GetAllSchoolResponse;
import kodlamaio.hrms.business.responses.schoolResonses.GetSchoolResponse;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.School;

public interface SchoolService {
	Result add(CreateSchoolRequest schoolRequest);
	DataResult<List<GetAllSchoolResponse>> getAll();
	DataResult<GetSchoolResponse> getById(int id);
	List<School> getByCv_id(int id);
	DataResult<List<GetAllSchoolResponse>> getByCv_Employee_Id(int id);
	DataResult<List<GetSchoolResponse>> getUserByCvIdSortedByGraduationDate(int userId, int sortType);
}
