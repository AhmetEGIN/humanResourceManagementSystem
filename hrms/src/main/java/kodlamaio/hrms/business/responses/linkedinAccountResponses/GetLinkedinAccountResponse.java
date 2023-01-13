package kodlamaio.hrms.business.responses.linkedinAccountResponses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetLinkedinAccountResponse {
	private String accountAddress;
	private int cvId;
}
