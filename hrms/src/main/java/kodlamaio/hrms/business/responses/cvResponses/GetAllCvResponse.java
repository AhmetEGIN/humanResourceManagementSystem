package kodlamaio.hrms.business.responses.cvResponses;

import java.util.List;

import kodlamaio.hrms.business.responses.employeeResponses.GetEmployeeResponse;
import kodlamaio.hrms.business.responses.jobExperienceResponses.GetAllJobExperienceResponse;
import kodlamaio.hrms.business.responses.languageResponses.GetAllLanguageResponse;
import kodlamaio.hrms.business.responses.schoolResonses.GetAllSchoolResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllCvResponse {
	
	private int cvId;
	private GetEmployeeResponse employee;
	private List<GetAllSchoolResponse> schools;
	private List<GetAllJobExperienceResponse> jobExperiences;
	private List<GetAllLanguageResponse> languages;
	
}
