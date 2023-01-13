package kodlamaio.hrms.business.requests.schoolRequests;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateSchoolRequest {
	
	private String name;
	private int cvId;
	private String departmentName;
	private LocalDate startYear;
	private LocalDate graduationYear;
}
