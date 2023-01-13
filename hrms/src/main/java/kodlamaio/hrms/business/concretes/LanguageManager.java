package kodlamaio.hrms.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.LanguageService;
import kodlamaio.hrms.business.requests.languageRequests.CreateLanguageRequest;
import kodlamaio.hrms.business.responses.languageResponses.GetAllLanguageResponse;
import kodlamaio.hrms.business.responses.languageResponses.GetLanguageResponse;
import kodlamaio.hrms.core.utilities.mapping.ModelMapperService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.LanguageRepository;
import kodlamaio.hrms.entities.concretes.Language;

@Service
public class LanguageManager implements LanguageService {
	
	private LanguageRepository languageRepository;
	private ModelMapperService mapperService;

	@Autowired
	public LanguageManager(LanguageRepository languageRepository, ModelMapperService mapperService) {
		this.languageRepository = languageRepository;
		this.mapperService = mapperService;
	}

	@Override
	public Result add(CreateLanguageRequest languageRequest) {
		Language language = mapperService.forRequest().map(languageRequest, Language.class);
		language.setId(0);
		this.languageRepository.save(language);
		return new SuccessResult();
	}

	@Override
	public DataResult<List<GetAllLanguageResponse>> getAll() {
		List<GetAllLanguageResponse> languageResponses = this.languageRepository.findAll().stream().map(
				language -> this.mapperService.forResponse().map(language, GetAllLanguageResponse.class)).collect(Collectors.toList());
				
		return new SuccessDataResult<List<GetAllLanguageResponse>>(languageResponses);
	}

	@Override
	public DataResult<GetLanguageResponse> getById(int id) {
		GetLanguageResponse languageResponse = this.mapperService.forResponse().map(languageRepository.findById(id), GetLanguageResponse.class);
		return new SuccessDataResult<GetLanguageResponse>(languageResponse);
	}
	
	
	
	
}
