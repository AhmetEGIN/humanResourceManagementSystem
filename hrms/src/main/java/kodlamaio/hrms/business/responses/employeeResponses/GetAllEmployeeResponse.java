package kodlamaio.hrms.business.responses.employeeResponses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllEmployeeResponse {
	private int id;
	private String firstName;
	private String lastName;
	private String identityNumber;
	private short birthYear;
	private String email;
	private String password;
	private String imagePath;
}
