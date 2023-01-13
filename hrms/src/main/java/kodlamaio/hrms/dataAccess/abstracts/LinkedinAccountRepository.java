package kodlamaio.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.hrms.entities.concretes.LinkedinAccount;

public interface LinkedinAccountRepository extends JpaRepository<LinkedinAccount, Integer> {
	boolean existsLinkedinAccountByIdEquals(int id);
}
