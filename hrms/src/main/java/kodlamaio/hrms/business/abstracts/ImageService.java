package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.business.requests.imageRequests.CreateImageRequest;
import kodlamaio.hrms.core.utilities.results.Result;

public interface ImageService {
	Result uploadImage(CreateImageRequest imageRequest);
}
