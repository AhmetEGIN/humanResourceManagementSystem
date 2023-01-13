package kodlamaio.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.hrms.entities.concretes.Technology;

public interface TechnologyRepository extends JpaRepository<Technology, Integer> {
	boolean existsTechnologyByIdEquals(int id);
}
