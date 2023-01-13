package kodlamaio.hrms.core.utilities.verifications;

import org.springframework.stereotype.Service;

import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessResult;

@Service
public class EmailVerificationManager implements VerificationService {

	@Override
	public String sendVerificationCode(String email) {
		String verificationCode = generateVerificationCode();
		return verificationCode;
	}
	@Override
	public Result isEmailVerified(String email) {
		return new SuccessResult();
	}

	private String generateVerificationCode() {
		String verifyCode = "AAA";
		return verifyCode;
	}
	@Override
	public Result verifyEmail() {
		return new SuccessResult();
	}
		
}
