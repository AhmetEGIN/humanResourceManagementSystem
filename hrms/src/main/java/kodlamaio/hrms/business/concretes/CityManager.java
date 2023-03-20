package kodlamaio.hrms.business.concretes;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.CityService;
import kodlamaio.hrms.business.constants.messages.Message;
import kodlamaio.hrms.business.requests.cityRequests.CreateCityRequest;
import kodlamaio.hrms.business.responses.cityResponses.GetCityResponse;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.CityRepository;
import kodlamaio.hrms.entities.concretes.City;

@Service
public class CityManager implements CityService {
	private CityRepository cityRepository;
	private ModelMapper modelMapper;
	
	@Autowired
	public CityManager(CityRepository cityRepository, ModelMapper modelMapper) {
		this.cityRepository = cityRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public Result add(CreateCityRequest cityRequest) {
		City city = modelMapper.map(cityRequest, City.class);
		this.cityRepository.save(city);
		return new SuccessResult(Message.CITY_ADDED);
	}

	@Override
	public DataResult<GetCityResponse> getById(int id) {
		GetCityResponse city = modelMapper.map(cityRepository.getReferenceById(id), GetCityResponse.class);
		return new SuccessDataResult<GetCityResponse>(city);
	}
	
	
}
