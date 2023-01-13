package kodlamaio.hrms.core.utilities.verifications;

import kodlamaio.hrms.core.utilities.results.Result;

public interface VerificationService {
	String sendVerificationCode(String email);
	Result isEmailVerified(String email);
	Result verifyEmail();
}
