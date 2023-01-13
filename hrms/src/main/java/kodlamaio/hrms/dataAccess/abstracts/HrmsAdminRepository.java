package kodlamaio.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.hrms.entities.concretes.HrmsAdmin;

public interface HrmsAdminRepository extends JpaRepository<HrmsAdmin, Integer> {

}
