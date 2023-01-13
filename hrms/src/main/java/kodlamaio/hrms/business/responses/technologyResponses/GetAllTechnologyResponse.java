package kodlamaio.hrms.business.responses.technologyResponses;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllTechnologyResponse {
	
	private int id;
	private String name;
	private LocalDate createdDate;
	private int cvId;
}
