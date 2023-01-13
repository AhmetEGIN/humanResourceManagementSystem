package kodlamaio.hrms.business.responses.jobAdvertisementResponses;

import java.sql.Date;

import kodlamaio.hrms.business.responses.cityResponses.GetCityResponse;
import kodlamaio.hrms.business.responses.employerResponses.GetEmployerResponse;
import kodlamaio.hrms.business.responses.positionResponses.GetPositionResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllJobAdvertisementResponse {
	
	private int id;
	private String description;
	private GetPositionResponse positionResponse;
	private GetCityResponse cityResponse;
	private GetEmployerResponse employerResponse;
	private Date deadline;
	private int minSalary;
	private int maxSalary;
	private int vacantPositionCount;

//	private String employerName;
//	private String positionName;
//	private LocalDate releaseDate;
//	private Date deadline;
	
}
