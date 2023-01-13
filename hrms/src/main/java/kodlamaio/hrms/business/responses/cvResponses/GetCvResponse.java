package kodlamaio.hrms.business.responses.cvResponses;

import java.util.List;

import kodlamaio.hrms.business.responses.coverLetterResponses.GetCoverLetterResponse;
import kodlamaio.hrms.business.responses.employeeResponses.GetEmployeeResponse;
import kodlamaio.hrms.business.responses.githubAccountResponses.GetGithubAccountResponse;
import kodlamaio.hrms.business.responses.jobExperienceResponses.GetJobExperienceResponse;
import kodlamaio.hrms.business.responses.languageResponses.GetLanguageResponse;
import kodlamaio.hrms.business.responses.linkedinAccountResponses.GetLinkedinAccountResponse;
import kodlamaio.hrms.business.responses.schoolResonses.GetSchoolResponse;
import kodlamaio.hrms.business.responses.technologyResponses.GetTechnologyResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetCvResponse {

	private GetEmployeeResponse employee;
	private List<GetSchoolResponse> schools;
	private List<GetJobExperienceResponse> jobExperiences;
	private List<GetLanguageResponse> languages;
	private GetGithubAccountResponse githubAccount;
	private GetLinkedinAccountResponse linkedinAccount;
	private GetCoverLetterResponse coverLetter;
	private List<GetTechnologyResponse> technologies;
}
