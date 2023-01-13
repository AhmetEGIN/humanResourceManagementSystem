package kodlamaio.hrms.business.responses.schoolResonses;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllSchoolResponse {
	
	private int schoolId;
	private String name;
	private String departmentName;
	private LocalDate startYear;
	private LocalDate graduationYear;
	private int cvId;
	
}
