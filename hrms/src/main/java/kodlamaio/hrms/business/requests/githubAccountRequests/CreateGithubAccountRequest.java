package kodlamaio.hrms.business.requests.githubAccountRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateGithubAccountRequest {
	
	private String accountAddress;
	private int cvId;
}
