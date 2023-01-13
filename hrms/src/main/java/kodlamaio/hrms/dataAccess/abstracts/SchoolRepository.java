package kodlamaio.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.hrms.entities.concretes.School;

public interface SchoolRepository extends JpaRepository<School, Integer> {
	List<School> getByCv_Id(int id, Sort sort);
	List<School> getByCv_Employee_Id(int id);
	
} 
