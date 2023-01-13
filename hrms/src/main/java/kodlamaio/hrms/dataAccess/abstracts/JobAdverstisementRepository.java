package kodlamaio.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.hrms.entities.concretes.JobAdvertisement;

public interface JobAdverstisementRepository extends JpaRepository<JobAdvertisement, Integer> {
	List<JobAdvertisement> getByIsActive(boolean state);
	List<JobAdvertisement> getByEmployer_Id(int id);
	
}
