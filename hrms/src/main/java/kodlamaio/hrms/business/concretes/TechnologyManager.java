package kodlamaio.hrms.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.TechnologyService;
import kodlamaio.hrms.business.requests.technologyRequests.CreateTechnologyRequest;
import kodlamaio.hrms.business.requests.technologyRequests.UpdateTechnologyRequest;
import kodlamaio.hrms.business.responses.technologyResponses.GetAllTechnologyResponse;
import kodlamaio.hrms.business.responses.technologyResponses.GetTechnologyResponse;
import kodlamaio.hrms.core.utilities.business.BusinessRules;
import kodlamaio.hrms.core.utilities.mapping.ModelMapperService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.TechnologyRepository;
import kodlamaio.hrms.entities.concretes.Technology;

@Service
public class TechnologyManager implements TechnologyService{
	private TechnologyRepository technologyRepository;
	private ModelMapperService mapperService;
	
	@Autowired
	public TechnologyManager(TechnologyRepository technologyRepository, ModelMapperService mapperService) {
		this.technologyRepository = technologyRepository;
		this.mapperService = mapperService;
	}

	@Override
	public Result add(CreateTechnologyRequest technologyRequest) {
		Technology technology = this.mapperService.forRequest().map(technologyRequest, Technology.class);
		technology.setId(0);
		this.technologyRepository.save(technology);
		return new SuccessResult();
	}

	@Override
	public Result update(int id, UpdateTechnologyRequest technologyRequest) {
		var result = BusinessRules.run(checkTechnologyExist(id));
		if (result != null) {
			return new ErrorResult();
		}
		Technology technology = this.technologyRepository.getReferenceById(id);
		updateTechnology(technology, technologyRequest);
		this.technologyRepository.save(technology);
		return new SuccessResult();
	}

	@Override
	public DataResult<List<GetAllTechnologyResponse>> getAll() {
		List<GetAllTechnologyResponse> technologyResponses = this.technologyRepository.findAll().stream().map(technology->this.mapperService.forResponse().map(technology, GetAllTechnologyResponse.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<GetAllTechnologyResponse>>(technologyResponses);
	}

	@Override
	public DataResult<GetTechnologyResponse> getById(int id) {
		GetTechnologyResponse technologyResponse = this.mapperService.forResponse().map(technologyRepository.findById(id), GetTechnologyResponse.class);
		return new SuccessDataResult<GetTechnologyResponse>(technologyResponse);
	}
	
	
	// private codes -- business rules
	private Result checkTechnologyExist(int id) {
		if (this.technologyRepository.existsTechnologyByIdEquals(id)) {
			return new SuccessResult();
		}
		return new ErrorResult();
	}
	
	private void updateTechnology(Technology technology, UpdateTechnologyRequest updateTechnologyRequest) {
		technology.setTechnologyName(updateTechnologyRequest.getTechnologyName());
	}

	@Override
	public Result deleteById(int id) {
		this.technologyRepository.deleteById(id);
		return new SuccessResult("Silindi");
	}
	
}
