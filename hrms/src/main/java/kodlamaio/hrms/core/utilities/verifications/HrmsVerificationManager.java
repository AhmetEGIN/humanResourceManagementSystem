package kodlamaio.hrms.core.utilities.verifications;

import org.springframework.stereotype.Service;

import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessResult;

@Service
public class HrmsVerificationManager implements VerficationByHrmsService {

	@Override
	public Result verify(String email) {
		return null;
	}

	@Override
	public Result isVerifiedFromHrms() {
		return new SuccessResult();
	}
	
}
