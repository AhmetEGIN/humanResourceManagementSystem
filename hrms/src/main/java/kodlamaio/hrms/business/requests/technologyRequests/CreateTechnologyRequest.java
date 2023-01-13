package kodlamaio.hrms.business.requests.technologyRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateTechnologyRequest {
	
	private String technologyName;
	private int cvId;
}
