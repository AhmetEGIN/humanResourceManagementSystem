package kodlamaio.hrms.business.responses.employerResponses;

import java.util.List;

import kodlamaio.hrms.entities.concretes.JobAdvertisement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllEmployerResponse {

	private int id;
	private String companyName;
	private String webSite;
	private String email;
	private String phone;
	private String password;
	private List<JobAdvertisement> jobAdvertisements;

}
