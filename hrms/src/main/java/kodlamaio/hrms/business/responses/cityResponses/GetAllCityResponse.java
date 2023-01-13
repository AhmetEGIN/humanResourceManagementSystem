package kodlamaio.hrms.business.responses.cityResponses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllCityResponse {
	private int id;
	private String name;
}
