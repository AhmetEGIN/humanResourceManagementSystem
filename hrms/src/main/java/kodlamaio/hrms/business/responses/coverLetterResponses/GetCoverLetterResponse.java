package kodlamaio.hrms.business.responses.coverLetterResponses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetCoverLetterResponse {
	private String description;
	private int cvId;
	
}
