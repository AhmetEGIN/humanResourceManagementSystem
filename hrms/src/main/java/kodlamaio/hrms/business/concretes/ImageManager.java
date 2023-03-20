package kodlamaio.hrms.business.concretes;



import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.ImageService;
import kodlamaio.hrms.business.adapters.cloudinary.ImageOutService;
import kodlamaio.hrms.business.constants.messages.Message;
import kodlamaio.hrms.business.requests.imageRequests.CreateImageRequest;
import kodlamaio.hrms.core.utilities.mapping.ModelMapperService;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.ImageRepository;
import kodlamaio.hrms.entities.concretes.Image;

@Service
public class ImageManager implements ImageService {

	private ImageOutService imageOutService;
	private ImageRepository imageRepository;
	private ModelMapperService mapperService;
	
	@Autowired
	public ImageManager(ImageOutService imageOutService, ImageRepository imageRepository, ModelMapperService mapperService) {
		this.imageOutService = imageOutService;
		this.imageRepository = imageRepository;
		this.mapperService = mapperService;
	}

	@Override
	public Result uploadImage(CreateImageRequest imageRequest) {
		var uploadResult = this.imageOutService.uploadImage(imageRequest.getFile());
		Image image = mapperService.forRequest().map(imageRequest, Image.class);
		image.setImagePath(uploadResult.getData());
		image.setId(0);
		imageRepository.save(image);
		return new SuccessResult(Message.IMAGE_ADDED);
	}

}
