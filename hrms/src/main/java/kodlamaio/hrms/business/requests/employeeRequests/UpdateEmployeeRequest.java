package kodlamaio.hrms.business.requests.employeeRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateEmployeeRequest {
	private int id;
	private String firstName;
	private String lastName;
	private String identityNumber;
	private short birthYear;
	private String email;
	private String password;
	private String confirmPassword;
	private boolean isVerified;
	private int imagePath;
}
