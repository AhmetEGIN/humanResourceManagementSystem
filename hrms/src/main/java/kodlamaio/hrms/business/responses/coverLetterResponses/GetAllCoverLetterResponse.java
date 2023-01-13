package kodlamaio.hrms.business.responses.coverLetterResponses;

import java.time.LocalDate;

import kodlamaio.hrms.business.responses.cvResponses.GetAllCvResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllCoverLetterResponse {
	
	private int id;
	private String description;
	private LocalDate createdDate;
	private GetAllCvResponse getAllCvResponse;
}
