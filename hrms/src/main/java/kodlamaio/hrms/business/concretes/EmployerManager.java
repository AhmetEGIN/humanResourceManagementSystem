package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.EmployerService;
import kodlamaio.hrms.business.constants.messages.Message;
import kodlamaio.hrms.business.requests.employerRequests.CreateEmployerRequest;
import kodlamaio.hrms.business.requests.employerRequests.UpdateEmployerRequest;
import kodlamaio.hrms.business.responses.employerResponses.GetAllEmployerResponse;
import kodlamaio.hrms.business.responses.employerResponses.GetEmployerResponse;
import kodlamaio.hrms.core.utilities.business.BusinessRules;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.EmployerRepository;
import kodlamaio.hrms.entities.concretes.Employer;

@Service
public class EmployerManager implements EmployerService {

	private EmployerRepository employerRepository;
	private ModelMapper modelMapper;

	@Autowired
	public EmployerManager(EmployerRepository employerRepository, ModelMapper modelMapper) {
		this.employerRepository = employerRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public Result add(CreateEmployerRequest employerRequest) {
//		List<Result> rules = new ArrayList<Result>();
//		Collections.addAll(rules,
//				checkEmailContainWebSiteDomain(employerRequest.getWebSite(), employerRequest.getEmail()),
//				checkIfEmailAlreadyExist(employerRequest.getEmail()));
		var result = BusinessRules.run(checkEmailContainWebSiteDomain(employerRequest.getWebSite(), employerRequest.getEmail()),
				checkIfEmailAlreadyExist(employerRequest.getEmail()));
		if (result == null) {
			Employer employer = modelMapper.map(employerRequest, Employer.class);
			this.employerRepository.save(employer);
			return new SuccessResult();
		}
		
		return new ErrorResult(result.getMessage());
	}

	@Override
	public DataResult<List<GetAllEmployerResponse>> getAll() {
		List<GetAllEmployerResponse> result = modelMapper.map(employerRepository.findAll(),
				new TypeToken<List<GetAllEmployerResponse>>() {
				}.getType());
		return new SuccessDataResult<List<GetAllEmployerResponse>>(result);
	}

	@Override
	public DataResult<GetEmployerResponse> getById(int id) {
		Employer employer = employerRepository.getReferenceById(id);
		GetEmployerResponse employerResponse = modelMapper.map(employer, GetEmployerResponse.class);
		employerResponse.setVerifiedFromAdmin(employer.isVerifiedFromAdmin());
		return new SuccessDataResult<GetEmployerResponse>(employerResponse);
	}
	
	@Override
	public Result update(UpdateEmployerRequest employerRequest) {
		Employer employer = modelMapper.map(getById(employerRequest.getId()).getData(), Employer.class);
		this.employerRepository.save(employer);
		return new SuccessResult();
		
	}
	
	public Result setVerifyFromAdmin(int employerId) {
		Employer employer = this.employerRepository.getReferenceById(employerId);
		employer.setVerifiedFromAdmin(true);
		this.employerRepository.save(employer);
		return new SuccessResult();
	}
	
	
	// business rules -- private codes
	private Result checkEmailContainWebSiteDomain(String webSite, String email) {
		if (email.contains(webSite)) {
			return new SuccessResult();
		}
		return new ErrorResult(Message.emailMustContaionWebSiteDomain);
	}

	private Result checkIfEmailAlreadyExist(String email) {
		if (!employerRepository.existsEmployerByEmailContainingIgnoreCase(email)) {
			return new SuccessResult();
		}
		return new ErrorResult(Message.emailAlreadyExist);
	}



}
