package kodlamaio.hrms.core.utilities.verifications;

import kodlamaio.hrms.core.utilities.results.Result;

public interface VerficationByHrmsService {
	Result verify(String email);
	Result isVerifiedFromHrms();
}
