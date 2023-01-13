package kodlamaio.hrms.core.dataAccess;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.hrms.core.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	Optional<User> findByEmail(String email);

	boolean existsUserByEmailContainingIgnoreCase(String email);

}
