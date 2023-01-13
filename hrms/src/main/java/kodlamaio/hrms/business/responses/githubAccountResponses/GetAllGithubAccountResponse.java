package kodlamaio.hrms.business.responses.githubAccountResponses;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllGithubAccountResponse {
	
	private int id;
	private String accountAddress;
	private LocalDate createdDate;
	private int cvId;
}
