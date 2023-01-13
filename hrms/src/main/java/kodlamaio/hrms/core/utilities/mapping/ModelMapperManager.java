package kodlamaio.hrms.core.utilities.mapping;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModelMapperManager implements ModelMapperService {

	private ModelMapper modelMapper;
	
	@Autowired
	public ModelMapperManager(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

	@Override
	public ModelMapper forResponse() {
		// setAmbiguityIgnored -- default değeri false. Birden fazla özellik ile eşleşen property'lerin göz ardı edilip edilemeyeceğini belirler
		//MatchingStrategies.Standart -- kaynak property'ler hedef property'lerle akıllı bir biçimde eşleşmesini sağlar. 
			//Tüm hedef property'ler en az bir kaynak property ile eşleşmelidir
		// MatchingStrategies.Strict -- kaynak property'lerle hedef property'lerin tam olarak eşleşmesini sağlar. 
			//kaynak ve hedef propertylerinin adı tam olarak birbirleri ile eşleşmelidir
		// MatchingStrategies.Loose -- farklı özellik hiyerarşilerine sahip kaynak ve hedef property'ler için gevşek bir eşleşme sağler
		this.modelMapper.getConfiguration().setAmbiguityIgnored(true).setMatchingStrategy(MatchingStrategies.LOOSE);
		return modelMapper;
	}

	@Override
	public ModelMapper forRequest() {
		this.modelMapper.getConfiguration().setAmbiguityIgnored(true).setMatchingStrategy(MatchingStrategies.STANDARD);
		return modelMapper;
	}

}
