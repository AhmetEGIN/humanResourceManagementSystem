package kodlamaio.hrms.business.responses.languageResponses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllLanguageResponse {
	private int id;
	private String name;
	private int languageLevel;
	private int cvId;
}
