package kodlamaio.hrms.business.responses.jobExperienceResponses;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllJobExperienceResponse {
	
	private int jobExperienceId;
	private String companyName;
	private String jobPosition;
	private LocalDate startDate;
	private LocalDate releaseDate;
	private int cvId;
	
	
		
}
