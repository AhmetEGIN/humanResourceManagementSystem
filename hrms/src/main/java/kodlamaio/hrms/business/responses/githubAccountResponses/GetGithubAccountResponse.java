package kodlamaio.hrms.business.responses.githubAccountResponses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetGithubAccountResponse {
	private String accountAddress;
	private int cvId;
}
