package kodlamaio.hrms.business.adapters.mernis;

import org.springframework.stereotype.Service;


import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.fakeMernisService.MernisService;
@Service
public class MernisServiceAdapter implements UserCheckService {

	@Override
	public Result checkPerson(String firstName, String lastName, String identityNumber, short birthYear) {
		MernisService mernisService = new MernisService();

		if (mernisService.validate(firstName, lastName, Long.parseLong(identityNumber), birthYear)) {
			return new SuccessResult();
		} else {
			return new ErrorResult();
		}
		
	}
}
