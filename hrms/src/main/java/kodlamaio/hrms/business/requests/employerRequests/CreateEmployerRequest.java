package kodlamaio.hrms.business.requests.employerRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateEmployerRequest {
	private String companyName;
	private String webSite;
	private String email;
	private String phone;
	private String password;
	private String confirmPassword;
}
