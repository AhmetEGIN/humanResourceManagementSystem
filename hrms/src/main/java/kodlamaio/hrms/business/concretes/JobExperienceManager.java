package kodlamaio.hrms.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.JobExperienceService;
import kodlamaio.hrms.business.requests.jobExperienceRequests.CreateJobExperienceRequest;
import kodlamaio.hrms.business.responses.jobExperienceResponses.GetAllJobExperienceResponse;
import kodlamaio.hrms.business.responses.jobExperienceResponses.GetJobExperienceResponse;
import kodlamaio.hrms.core.utilities.mapping.ModelMapperService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.JobExperienceRepository;
import kodlamaio.hrms.entities.concretes.JobExperience;

@Service
public class JobExperienceManager implements JobExperienceService {
	
	private JobExperienceRepository experienceRepository;
	private ModelMapperService mapperService;
	
	@Autowired
	public JobExperienceManager(JobExperienceRepository experienceRepository, ModelMapperService mapperService) {
		this.experienceRepository = experienceRepository;
		this.mapperService = mapperService;
	}

	@Override
	public Result add(CreateJobExperienceRequest jobExperienceRequest) {
		JobExperience jobExperience = mapperService.forRequest().map(jobExperienceRequest, JobExperience.class);
		jobExperience.setId(0);
		this.experienceRepository.save(jobExperience);
		return new SuccessResult();
	}

	@Override
	public DataResult<List<GetAllJobExperienceResponse>> getAll() {
		List<GetAllJobExperienceResponse> experienceResponses = this.experienceRepository.findAll().stream().map(
				experience->mapperService.forResponse().map(experience, GetAllJobExperienceResponse.class)).collect(Collectors.toList());
				
		return new SuccessDataResult<List<GetAllJobExperienceResponse>>(experienceResponses);
	}

	@Override
	public DataResult<GetJobExperienceResponse> getById(int id) {
		GetJobExperienceResponse experienceResponse = mapperService.forResponse().map(experienceRepository.findById(id), GetJobExperienceResponse.class);
		return new SuccessDataResult<GetJobExperienceResponse>(experienceResponse);
	}

	@Override
	public DataResult<List<GetJobExperienceResponse>> getJobExperiencesByCvIdSortedByYear(int sortType, int cvId) {
		Sort sort = Sort.unsorted();
		if (sortType == 1) {
			sort = Sort.by(Direction.ASC, "releaseDate");
		} else {
			sort = Sort.by(Direction.DESC, "releaseDate");

		}
		
		List<GetJobExperienceResponse> experienceResponses = this.experienceRepository.getByCv_Id(cvId, sort).stream().map(experience->this.mapperService.forResponse().map(experience, GetJobExperienceResponse.class)).collect(Collectors.toList());
		
		return new SuccessDataResult<List<GetJobExperienceResponse>>(experienceResponses);
	}
	
}
