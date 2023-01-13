package kodlamaio.hrms.business.requests.languageRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateLanguageRequest {
	private String name;
	private int languageLevel;
	private int cvCvId;
}
