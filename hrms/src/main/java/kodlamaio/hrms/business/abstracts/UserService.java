package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.core.entities.User;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;

public interface UserService {
	Result setVerify(int userId);
	DataResult<User> getUser(int userId);
	Result isEmailExists(String email);
}
