package kodlamaio.hrms.business.responses.positionResponses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllPositionResponse {
	
	private int id;
	private String name;
}
