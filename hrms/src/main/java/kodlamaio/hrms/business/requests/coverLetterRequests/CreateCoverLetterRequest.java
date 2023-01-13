package kodlamaio.hrms.business.requests.coverLetterRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCoverLetterRequest {
	
	private String description;
	private int cvId;
}
