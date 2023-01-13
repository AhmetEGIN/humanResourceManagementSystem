package kodlamaio.hrms.business.requests.LinkedinAccountRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateLinkedinAccountRequest {
	
	private String accountAddress;
	private int cvId;
}
