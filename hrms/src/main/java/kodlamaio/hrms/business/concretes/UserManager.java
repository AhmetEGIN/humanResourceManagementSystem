package kodlamaio.hrms.business.concretes;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.UserService;
import kodlamaio.hrms.business.constants.messages.Message;
import kodlamaio.hrms.core.dataAccess.UserRepository;
import kodlamaio.hrms.core.entities.User;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorDataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;

@Service
public class UserManager implements UserService{
	private UserRepository userRepository;
	
	@Autowired
	public UserManager(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public Result setVerify(int userId) {
		User userToUpdate = this.userRepository.getReferenceById(userId);
		
		verifyUser(userToUpdate);
		return new SuccessResult();
	}
	

	public DataResult<User> getUser(int userId) {
		User user = this.userRepository.findById(userId).get();
		return new SuccessDataResult<User>(user);
	}
	 
	
	// private codes
	private Result verifyUser(User user) {
		user.setEmailVerified(true);
		user.setActive(true);
		this.userRepository.save(user);
		return new SuccessResult();
	}
	
	@Override
	public Result isEmailExists(String email) {
		boolean state = this.userRepository.existsUserByEmailEquals(email);
		Optional<User> users = this.userRepository.findByEmail(email);
		if (users.isPresent()) {
			return new ErrorResult(Message.emailAlreadyExist);
		}
		return new SuccessResult();
	}


	@Override
	public DataResult<User> getByEmail(String email) {
		var user = this.userRepository.findByEmail(email).orElseThrow();
		if (user == null) {
			return new ErrorDataResult<>(Message.USER_NOT_FOUND);
		}
		return new SuccessDataResult<User>(user);
		
	}


}
