package kodlamaio.hrms.business.responses.employerResponses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetEmployerResponse {
	
	
	private String companyName;
	private String webSite;
	private String email;
	private String phone;
	private String password;
	private boolean isVerifiedFromAdmin;
}
