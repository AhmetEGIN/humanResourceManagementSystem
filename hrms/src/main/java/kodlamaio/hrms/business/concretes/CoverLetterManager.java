package kodlamaio.hrms.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.CoverLetterService;
import kodlamaio.hrms.business.requests.coverLetterRequests.CreateCoverLetterRequest;
import kodlamaio.hrms.business.requests.coverLetterRequests.UpdateCoverLetterRequest;
import kodlamaio.hrms.business.responses.coverLetterResponses.GetAllCoverLetterResponse;
import kodlamaio.hrms.business.responses.coverLetterResponses.GetCoverLetterResponse;
import kodlamaio.hrms.core.utilities.business.BusinessRules;
import kodlamaio.hrms.core.utilities.mapping.ModelMapperService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.CoverLetterRepository;
import kodlamaio.hrms.entities.concretes.CoverLetter;

@Service
public class CoverLetterManager implements CoverLetterService {
	
	private CoverLetterRepository coverLetterRepository;
	private ModelMapperService mapperService;
	
	@Autowired
	public CoverLetterManager(CoverLetterRepository coverLetterRepository, ModelMapperService mapperService) {
		this.coverLetterRepository = coverLetterRepository;
		this.mapperService = mapperService;
	}

	@Override
	public Result add(CreateCoverLetterRequest coverLetterRequest) {
		CoverLetter coverLetter = mapperService.forRequest().map(coverLetterRequest, CoverLetter.class);
		coverLetter.setId(0);
		this.coverLetterRepository.save(coverLetter);
		return new SuccessResult();
	}

	@Override
	public DataResult<List<GetAllCoverLetterResponse>> getAll() {
		List<GetAllCoverLetterResponse> coverLetterResponses = this.coverLetterRepository.findAll().stream().map(letter->this.mapperService.forResponse().map(letter, GetAllCoverLetterResponse.class)).collect(Collectors.toList());
		
		return new SuccessDataResult<List<GetAllCoverLetterResponse>>(coverLetterResponses);
	}

	@Override
	public DataResult<GetCoverLetterResponse> getById(int id) {
		GetCoverLetterResponse coverLetterResponse = this.mapperService.forResponse().map(coverLetterRepository.findById(id), GetCoverLetterResponse.class);
		return new SuccessDataResult<GetCoverLetterResponse>(coverLetterResponse);
	}

	@Override
	public Result update(int id, UpdateCoverLetterRequest coverLetterRequest) {
		var result = BusinessRules.run(checkIfCoverLetterExistById(id));
		if (result != null) {
			return new ErrorResult();
		}
		CoverLetter coverLetter = this.coverLetterRepository.getReferenceById(id);
		updateCoverLetter(coverLetter, coverLetterRequest);
		this.coverLetterRepository.save(coverLetter);
		return new SuccessResult();
	}
	
	
	// private codes
	private Result checkIfCoverLetterExistById(int id) {
		boolean response = this.coverLetterRepository.existsById(id);
		if (response) {
			return new SuccessResult();
		}
		return new ErrorResult();
	}
	
	private void updateCoverLetter(CoverLetter coverLetter, UpdateCoverLetterRequest coverLetterRequest) {
		coverLetter.setDescription(coverLetterRequest.getDescription());
	}

}
