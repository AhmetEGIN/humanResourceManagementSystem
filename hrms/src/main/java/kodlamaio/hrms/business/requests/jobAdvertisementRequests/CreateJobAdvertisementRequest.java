package kodlamaio.hrms.business.requests.jobAdvertisementRequests;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateJobAdvertisementRequest {
	
	private String description;
	private int positionId;
	private int cityId;
	private int employerId;
	private Date deadline;
	private int minSalary;
	private int maxSalary;
	private int vacantPositionCount;
}
