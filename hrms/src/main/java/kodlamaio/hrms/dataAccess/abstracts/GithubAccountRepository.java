package kodlamaio.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.hrms.entities.concretes.GithubAccount;

public interface GithubAccountRepository extends JpaRepository<GithubAccount, Integer> {
	boolean existsGithubAccountByIdEquals(int id);
}
