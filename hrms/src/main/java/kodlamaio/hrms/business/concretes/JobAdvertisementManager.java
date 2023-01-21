package kodlamaio.hrms.business.concretes;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.JobAdvertisementService;
import kodlamaio.hrms.business.constants.messages.Message;
import kodlamaio.hrms.business.requests.jobAdvertisementRequests.CreateJobAdvertisementRequest;
import kodlamaio.hrms.business.responses.jobAdvertisementResponses.GetAllJobAdvertisementResponse;
import kodlamaio.hrms.business.responses.jobAdvertisementResponses.GetJobAdvertisementResponse;
import kodlamaio.hrms.core.utilities.mapping.ModelMapperService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.JobAdverstisementRepository;
import kodlamaio.hrms.entities.concretes.JobAdvertisement;

@Service
public class JobAdvertisementManager implements JobAdvertisementService {

	private JobAdverstisementRepository adverstisementRepository;
	private ModelMapperService modelMapperService;


	@Autowired
	public JobAdvertisementManager(JobAdverstisementRepository adverstisementRepository, ModelMapperService modelMapperService) {
		this.adverstisementRepository = adverstisementRepository;
		this.modelMapperService = modelMapperService;

	}

	@Override
	public Result add(CreateJobAdvertisementRequest jobAdvertisement) {

//		// !!!!!! Buraya düzenleme gerekli !!!!!!!
//		JobAdvertisement advertisement = new JobAdvertisement();
//		advertisement.setDescription(jobAdvertisement.getDescription());
//		advertisement.setMinSalary(jobAdvertisement.getMinSalary());
//		advertisement.setMaxSalary(jobAdvertisement.getMaxSalary());
//		advertisement.setDeadline(jobAdvertisement.getDeadline());
//		advertisement.setVacantPositionCount(jobAdvertisement.getVacantPositionCount());
//		advertisement.setEmployer(modelMapper.map(employerService.getById(jobAdvertisement.getEmployerId()).getData(),Employer.class ));
//		advertisement.setPosition(modelMapper.map(positionService.getPositionById(jobAdvertisement.getPositionId()).getData(), Position.class));
//		advertisement.setCity(modelMapper.map(cityService.getCity(jobAdvertisement.getCityId()).getData(), City.class));
//		this.adverstisementRepository.save(advertisement);
		// modelmapper configuration u ile düzenleme yapıldı
		
		JobAdvertisement advertisement = modelMapperService.forRequest().map(jobAdvertisement, JobAdvertisement.class);
		advertisement.setId(0);
		this.adverstisementRepository.save(advertisement);
		return new SuccessResult(Message.jobAdvertisementAdded);
	}

	@Override
	public DataResult<GetJobAdvertisementResponse> getById(int id) {

		JobAdvertisement jobAdvertisement = this.adverstisementRepository.getReferenceById(id);
		GetJobAdvertisementResponse advertisementResponse = this.modelMapperService.forResponse().map(jobAdvertisement, GetJobAdvertisementResponse.class);
		return new SuccessDataResult<GetJobAdvertisementResponse>(advertisementResponse);
	}

	@Override
	public DataResult<List<GetAllJobAdvertisementResponse>> getAllJobAdvertisements() {

		
		List<GetAllJobAdvertisementResponse> getAllJobAdvertisementResponses = this.adverstisementRepository.findAll().stream().map(
				jobAdvertisement -> this.modelMapperService.forResponse().map(jobAdvertisement, GetAllJobAdvertisementResponse.class)).collect(Collectors.toList());
						
		return new SuccessDataResult<List<GetAllJobAdvertisementResponse>>(getAllJobAdvertisementResponses, Message.jobAdvertisementsListed);

	}

	@Override
	public DataResult<List<GetAllJobAdvertisementResponse>> getAllActiveJobAdvertisements() {
		List<JobAdvertisement> jobAdvertisements = this.adverstisementRepository.getByIsActive(true);
		List<GetAllJobAdvertisementResponse> getAllJobAdvertisementResponses = new ArrayList<GetAllJobAdvertisementResponse>();
		getAllJobAdvertisementResponses = jobAdvertisements.stream().map(advertisement->this.modelMapperService.forResponse().map(advertisement, GetAllJobAdvertisementResponse.class)).collect(Collectors.toList());
		
//		for(JobAdvertisement advertisement : jobAdvertisements) {
//			isJobAdvertisementActive(advertisement);
//			getAllJobAdvertisementResponses.add(this.modelMapperService.forResponse().map(advertisement, GetAllJobAdvertisementResponse.class));
//		}
		
		return new SuccessDataResult<List<GetAllJobAdvertisementResponse>>(getAllJobAdvertisementResponses, Message.activeJobAdvertisementListed);
	}

	@Override
	public DataResult<List<GetAllJobAdvertisementResponse>> getAllJobAdvertisementsByEmployer(int employerId) {
		List<JobAdvertisement> jobAdvertisements = this.adverstisementRepository.getByEmployer_Id(employerId);
		List<GetAllJobAdvertisementResponse> jobAdvertisementResponses = new ArrayList<GetAllJobAdvertisementResponse>();
		jobAdvertisementResponses = jobAdvertisements.stream().map(advertisement->this.modelMapperService.forResponse().map(advertisement, GetAllJobAdvertisementResponse.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<GetAllJobAdvertisementResponse>>(jobAdvertisementResponses, Message.jobAdvertisementsByEmployerListed);
	}

	@Override
	public Result toggleStateOfActive(int jobId, boolean state) {
		JobAdvertisement jobAdvertisement = this.adverstisementRepository.getReferenceById(jobId);
		jobAdvertisement.setActive(state);
		this.adverstisementRepository.save(jobAdvertisement);
		return new SuccessResult(Message.stateOfBeingActiveChanged);
	}

	@Override
	public DataResult<List<GetAllJobAdvertisementResponse>> getAllActiveJobAdvertisements(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo-1, pageSize);
		List<GetAllJobAdvertisementResponse> getAllJobAdvertisementResponses = this.adverstisementRepository.findAll(pageable).getContent().stream().map(advertisement -> this.modelMapperService.forResponse().map(advertisement, GetAllJobAdvertisementResponse.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<GetAllJobAdvertisementResponse>>(getAllJobAdvertisementResponses);
	}

	@Override
	public DataResult<List<GetAllJobAdvertisementResponse>> getAllActiveJobAdvertisementsSortedByDeadline(int sort) {
		Sort sorting = Sort.unsorted();
		if (sort == 1) {
			sorting = Sort.by(Sort.Direction.ASC, "deadline");
		} else if(sort == 2) {
			sorting = Sort.by(Sort.Direction.DESC, "deadline");
		}
		List<GetAllJobAdvertisementResponse> getAllJobAdvertisementResponses = this.adverstisementRepository.findAll(sorting).stream().map(advertisement -> this.modelMapperService.forResponse().map(advertisement, GetAllJobAdvertisementResponse.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<GetAllJobAdvertisementResponse>>(getAllJobAdvertisementResponses);
	}
	
	
	
	
	// business codes -- private codes
	
//	private Result isJobAdvertisementActive(JobAdvertisement advertisement) {
//		if (advertisement.isActive()) {
//			return new SuccessResult();
//		}
//		return new ErrorResult();
//	}
	
}
