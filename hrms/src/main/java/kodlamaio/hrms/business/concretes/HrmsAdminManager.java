package kodlamaio.hrms.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.EmployerService;
import kodlamaio.hrms.business.abstracts.HrmsAdminService;
import kodlamaio.hrms.business.abstracts.UserService;
import kodlamaio.hrms.business.constants.messages.Message;
import kodlamaio.hrms.business.requests.hrmsAdminRequests.CreateHrmsAdminRequest;
import kodlamaio.hrms.core.utilities.business.BusinessRules;
import kodlamaio.hrms.core.utilities.mapping.ModelMapperService;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.HrmsAdminRepository;
import kodlamaio.hrms.entities.concretes.HrmsAdmin;

@Service
public class HrmsAdminManager implements HrmsAdminService {
	
	private HrmsAdminRepository hrmsAdminRepository;
	private EmployerService employerService;
	private ModelMapperService modelMapperService;
	private UserService userService;
	
	@Autowired
	public HrmsAdminManager(HrmsAdminRepository hrmsAdminRepository, EmployerService employerService,
			ModelMapperService modelMapperService, UserService userService) {
		this.hrmsAdminRepository = hrmsAdminRepository;
		this.employerService = employerService;
		this.modelMapperService = modelMapperService;
		this.userService = userService;
	}

	
	@Override
	public Result add(CreateHrmsAdminRequest hrmsAdminRequest) {
		HrmsAdmin hrmsAdmin = modelMapperService.forRequest().map(hrmsAdminRequest, HrmsAdmin.class);
		this.hrmsAdminRepository.save(hrmsAdmin);
		return new SuccessResult();
	}

	@Override
	public Result verifyEmployer(int employerId) {
		var result = BusinessRules.run(isEmailValid(employerId));
		if (result != null) {
			return result;
		}
		this.employerService.setVerifyFromAdmin(employerId);
		return new SuccessResult();
	}
	
	// private codes
	
	private Result isEmailValid(int employerId) {
		boolean state =this.userService.getUser(employerId).getData().isEmailVerified();
		if (state) {
			return new SuccessResult();
		} else {
			return new ErrorResult(Message.EMAIL_MUST_BE_VERİFİED_FİRST);
		}
	}
}
