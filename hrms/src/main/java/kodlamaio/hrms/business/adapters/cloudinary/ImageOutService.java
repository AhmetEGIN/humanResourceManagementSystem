package kodlamaio.hrms.business.adapters.cloudinary;

import org.springframework.web.multipart.MultipartFile;

import kodlamaio.hrms.core.utilities.results.DataResult;

public interface ImageOutService {

	DataResult<String> uploadImage(MultipartFile file);
	
}
