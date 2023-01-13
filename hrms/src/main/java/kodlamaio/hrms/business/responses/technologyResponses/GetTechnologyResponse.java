package kodlamaio.hrms.business.responses.technologyResponses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetTechnologyResponse {
	
	private String name;
	private int cvId;
}
