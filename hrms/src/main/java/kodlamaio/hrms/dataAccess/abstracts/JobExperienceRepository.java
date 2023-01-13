package kodlamaio.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.hrms.entities.concretes.JobExperience;

public interface JobExperienceRepository extends JpaRepository<JobExperience, Integer> {
	List<JobExperience> getByCv_Id(int id, Sort sort);
	
}
