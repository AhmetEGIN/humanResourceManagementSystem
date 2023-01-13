package kodlamaio.hrms.business.adapters.mernis;

import kodlamaio.hrms.core.utilities.results.Result;

public interface UserCheckService {
	Result checkPerson(String firstName, String lastName, String identityNumber, short birthYear);
}
