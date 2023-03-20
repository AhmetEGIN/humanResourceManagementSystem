package kodlamaio.hrms.business.requests.authRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserGoogleRequest {
	
	private String email;
	private boolean verified_email;
	private String name;
	private String given_name;
	private String family_name;
	private String picture;
	
	
}
