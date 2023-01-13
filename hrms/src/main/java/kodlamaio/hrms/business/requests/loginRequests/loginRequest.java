package kodlamaio.hrms.business.requests.loginRequests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class loginRequest {
	
	private String email;
	private String password;
	
}
