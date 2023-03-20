package kodlamaio.hrms.business.concretes;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.PositionService;
import kodlamaio.hrms.business.constants.messages.Message;
import kodlamaio.hrms.business.requests.positionRequests.CreatePositionRequest;
import kodlamaio.hrms.business.responses.positionResponses.GetAllPositionResponse;
import kodlamaio.hrms.business.responses.positionResponses.GetPositionResponse;
import kodlamaio.hrms.core.utilities.business.BusinessRules;
import kodlamaio.hrms.core.utilities.mapping.ModelMapperService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.PositionRepository;
import kodlamaio.hrms.entities.concretes.Position;

@Service
public class PositionManager implements PositionService {

	private PositionRepository positionRepository;
	private ModelMapperService modelMapperService;

	@Autowired
	public PositionManager(PositionRepository positionRepository, ModelMapperService modelMapperService) {
		this.positionRepository = positionRepository;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public DataResult<List<GetAllPositionResponse>> getAll() {
		List<GetAllPositionResponse> positions = this.positionRepository.findAll().stream()
				.map(position -> this.modelMapperService.forResponse().map(position, GetAllPositionResponse.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<GetAllPositionResponse>>(positions);
	}

	@Override
	public Result add(CreatePositionRequest positionRequest) {
//		List<Result> rules = new ArrayList<Result>();
//		Collections.addAll(rules, checkPositionNameAlreadyExist(positionRequest.getName()));
		var result = BusinessRules.run(checkPositionNameAlreadyExist(positionRequest.getName()));
		if (result != null) {
			return new ErrorResult(result.getMessage());
		}
		Position position = this.modelMapperService.forRequest().map(positionRequest, Position.class);
		position.setId(0);
		this.positionRepository.save(position);
		return new SuccessResult(Message.POSITION_ADDED);

	}

	@Override
	public DataResult<GetPositionResponse> getPositionById(int id) {
		GetPositionResponse position = modelMapperService.forResponse().map(positionRepository.getReferenceById(id),
				GetPositionResponse.class);
		return new SuccessDataResult<GetPositionResponse>(position);
	}

	private Result checkPositionNameAlreadyExist(String positonName) {
		if (!positionRepository.existsPositionByName(positonName)) {
			return new SuccessResult();
		}
		return new ErrorResult(Message.POSITION_NAME_ALREADY_EXIST);
	}

}
