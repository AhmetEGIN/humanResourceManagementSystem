package kodlamaio.hrms.business.requests.employeeRequests;

import kodlamaio.hrms.core.entities.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateEmployeeRequest {
	private String firstName;
	private String lastName;
	private String identityNumber;
	private short birthYear;
	private String email;
	private String password;
	private String confirmPassword;
	private Role userRole;
}
	