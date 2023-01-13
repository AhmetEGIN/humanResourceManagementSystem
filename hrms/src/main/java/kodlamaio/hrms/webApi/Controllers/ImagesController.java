package kodlamaio.hrms.webApi.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.hrms.business.abstracts.ImageService;
import kodlamaio.hrms.business.requests.imageRequests.CreateImageRequest;
import kodlamaio.hrms.core.utilities.results.Result;

@RestController
@RequestMapping("/api/image")
public class ImagesController {
	
	private ImageService imageService;

	@Autowired
	public ImagesController(ImageService imageService) {
		this.imageService = imageService;
	}
	
	@PostMapping("/uploadImage")
	public Result uploadImage(@ModelAttribute CreateImageRequest imageRequest) {
		return this.imageService.uploadImage(imageRequest);
	}
	
	
}
