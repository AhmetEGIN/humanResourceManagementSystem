package kodlamaio.hrms.business.requests.jobExperienceRequests;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateJobExperienceRequest {
	
	private int cvId;
	private String companyName;
	private String jobPosition;
	private LocalDate startDate;
	private LocalDate releaseDate;
}
