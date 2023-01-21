package kodlamaio.hrms.business.requests.hrmsAdminRequests;

import java.util.List;

import kodlamaio.hrms.business.responses.employerResponses.GetAllEmployerResponse;
import kodlamaio.hrms.core.entities.enums.Role;
import kodlamaio.hrms.entities.concretes.JobAdvertisement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateHrmsAdminRequest {
	private String firstName;
	private String lastName;
	private String identityNumber;
	private short birthYear;
	private String email;
	private String password;
	private String confirmPassword;
	private Role userRole;
}
