package kodlamaio.hrms.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.CvService;
import kodlamaio.hrms.business.requests.cvRequests.CreateCvRequest;
import kodlamaio.hrms.business.responses.cvResponses.GetAllCvResponse;
import kodlamaio.hrms.business.responses.cvResponses.GetCvResponse;
import kodlamaio.hrms.core.utilities.mapping.ModelMapperService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.CvRepository;
import kodlamaio.hrms.entities.concretes.Cv;

@Service
public class CvManager implements CvService {
	private CvRepository cvRepository;
	private ModelMapperService mapperService;

	

	@Autowired
	public CvManager(CvRepository cvRepository, ModelMapperService mapperService) {
		this.cvRepository = cvRepository;
		this.mapperService = mapperService;
	}

	@Override
	public Result add(CreateCvRequest cvRequest) {
		Cv cv = this.mapperService.forRequest().map(cvRequest, Cv.class);
		cv.setId(0);
//		Cv cv = new Cv();
//		cv.setEmployee(this.employeeService.getEmployee(cvRequest.getEmployeeEmployeeId()));
//		cv.setEmployee(this.mapperService.forResponse().map(this.employeeService.getEmployeeDetails(cvRequest.getEmployee_id()).getData(), Employee.class));
		this.cvRepository.save(cv);
		return new SuccessResult();
	}

	@Override
	public DataResult<List<GetAllCvResponse>> getAll() {
		List<GetAllCvResponse> cvResponses = this.cvRepository.findAll().stream().map(
				cv->this.mapperService.forResponse().map(cv, GetAllCvResponse.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<GetAllCvResponse>>(cvResponses);
	}

	@Override
	public DataResult<GetCvResponse> getById(int id) {
		GetCvResponse cvResponse = this.mapperService.forResponse().map(cvRepository.getReferenceById(id), GetCvResponse.class);
		return new SuccessDataResult<GetCvResponse>(cvResponse);
	}
	
	@Override
	public Cv getCv(int id) {
		return this.cvRepository.getReferenceById(id);
	}
	
}
