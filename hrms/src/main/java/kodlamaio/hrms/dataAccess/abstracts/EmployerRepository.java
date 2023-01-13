package kodlamaio.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.hrms.entities.concretes.Employer;

public interface EmployerRepository extends JpaRepository<Employer, Integer> {
	List<Employer> findByEmailEquals(String email);
	boolean existsEmployerByEmailContainingIgnoreCase(String email);
}
