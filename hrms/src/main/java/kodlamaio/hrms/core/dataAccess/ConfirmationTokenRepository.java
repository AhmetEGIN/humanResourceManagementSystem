package kodlamaio.hrms.core.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.hrms.core.utilities.security.ConfirmationToken;

public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Integer> {

}
