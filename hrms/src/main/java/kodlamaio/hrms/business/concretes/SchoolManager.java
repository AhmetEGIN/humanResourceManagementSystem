package kodlamaio.hrms.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.SchoolService;
import kodlamaio.hrms.business.requests.schoolRequests.CreateSchoolRequest;
import kodlamaio.hrms.business.responses.schoolResonses.GetAllSchoolResponse;
import kodlamaio.hrms.business.responses.schoolResonses.GetSchoolResponse;
import kodlamaio.hrms.core.utilities.mapping.ModelMapperService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.SchoolRepository;
import kodlamaio.hrms.entities.concretes.School;

@Service
public class SchoolManager implements SchoolService {
	private SchoolRepository schoolRepository;
	private ModelMapperService mapperService;

	
	@Autowired
	public SchoolManager(SchoolRepository schoolRepository, ModelMapperService mapperService) {
		this.schoolRepository = schoolRepository;
		this.mapperService = mapperService;
	}

	@Override
	public Result add(CreateSchoolRequest schoolRequest) {
		School school = mapperService.forRequest().map(schoolRequest, School.class);
		school.setId(0);
		
//		School school = new School();
//		school.setName(schoolRequest.getName());
//		school.setDepartmentName(schoolRequest.getDepartmentName());
//		school.setGraduationYear(schoolRequest.getGraduationYear());
//		school.setStartYear(schoolRequest.getStartYear());
//		school.setCv(this.mapperService.forResponse().map(this.cvService.getCv(schoolRequest.getCvId()), Cv.class));
		this.schoolRepository.save(school);
		return new SuccessResult();
	}
 
	@Override
	public DataResult<List<GetAllSchoolResponse>> getAll() {
		List<GetAllSchoolResponse> schoolResponses = this.schoolRepository.findAll().stream()
				.map(school -> this.mapperService.forResponse().map(school, GetAllSchoolResponse.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<GetAllSchoolResponse>>(schoolResponses);
	}

	@Override
	public DataResult<GetSchoolResponse> getById(int id) {
		GetSchoolResponse schoolResponse = this.mapperService.forResponse().map(schoolRepository.getReferenceById(id), GetSchoolResponse.class);

		return new SuccessDataResult<GetSchoolResponse>(schoolResponse);
	}

	@Override
	public List<School> getByCv_id(int id) {
		return this.getByCv_id(id);
	}

	@Override
	public DataResult<List<GetAllSchoolResponse>> getByCv_Employee_Id(int id) {
		List<GetAllSchoolResponse> schoolResponses = this.schoolRepository.getByCv_Employee_Id(id).stream().map(
				school->this.mapperService.forResponse().map(school, GetAllSchoolResponse.class)).collect(Collectors.toList());
		
		return new SuccessDataResult<List<GetAllSchoolResponse>>(schoolResponses);
	}

	@Override
	public DataResult<List<GetSchoolResponse>> getUserByCvIdSortedByGraduationDate(int userId, int sortType) {
		Sort sort = Sort.unsorted();
		if (sortType == 1) {
			sort = Sort.by(Direction.ASC, "graduationYear");
		} else {
			sort = Sort.by(Direction.DESC, "graduationYear");
		}
		
		List<GetSchoolResponse> schoolResponses = this.schoolRepository.getByCv_Id(userId, sort).stream().map(school->this.mapperService.forResponse().map(school, GetSchoolResponse.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<GetSchoolResponse>>(schoolResponses);
	}

}
