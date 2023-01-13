package kodlamaio.hrms.business.responses.linkedinAccountResponses;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllLinkedinAccountResponse {
	
	private int id;
	private String accountAddress;
	private LocalDate createdDate;
	private int cvId;
}
