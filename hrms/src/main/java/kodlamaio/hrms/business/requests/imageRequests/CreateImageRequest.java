package kodlamaio.hrms.business.requests.imageRequests;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateImageRequest {
	private int employeeId;
	private MultipartFile file;
}
