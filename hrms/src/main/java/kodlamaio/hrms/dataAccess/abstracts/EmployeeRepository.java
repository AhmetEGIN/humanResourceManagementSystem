package kodlamaio.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import kodlamaio.hrms.business.responses.employeeResponses.GetEmployeeResponse;
import kodlamaio.hrms.entities.concretes.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	
	List<Employee> findByEmailEquals(String email);
	List<Employee> findByIdentityNumberEquals(String identityNumber);
	
	@Query("Select new kodlamaio.hrms.business.responses.employeeResponses.GetEmployeeResponse(e.id, e.firstName, e.lastName, e.identityNumber, e.birthYear, e.email, e.password, i.imagePath) From Employee e Inner Join e.images i where e.id=:employeeId ")
	GetEmployeeResponse getEmployeeDetails(int employeeId);
	
	
}
