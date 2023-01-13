package kodlamaio.hrms.business.adapters.cloudinary;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorDataResult;

@Service
public class CloudinaryManager implements ImageOutService {

	private Cloudinary cloudinary;
	
	@Autowired
	public CloudinaryManager() {
		
		Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
				"cloud_name", "javacloudinary",
				"api_key", "337575961245993",
				"api_secret", "NE0dIbc_uIVZ2tKbFKI6wzYSYa0",
				"secure", true));
		this.cloudinary = cloudinary;
	}

	@Override
	public DataResult<String> uploadImage(MultipartFile file) {
		try {
			File _file = convertFile(file);
			var uploadResult = this.cloudinary.uploader().upload(_file, ObjectUtils.emptyMap());
			// Cloudinary file türünde dosya istiyor. MultipartFile ı File türüne convert etmek için mulitpartFile 'ın byte larını .getBytes() metodu ile çekmemiz lazım.
			String imagePath = uploadResult.get("url").toString();
			return new DataResult<String>(imagePath, true);

		} catch (IOException e) {
			e.printStackTrace();
			return new ErrorDataResult<String>();
		}

	}
	
	
	private File convertFile(MultipartFile multipartFile) throws IOException {
		File _file = new File(multipartFile.getOriginalFilename());
		FileOutputStream stream = new FileOutputStream(_file);
		stream.write(multipartFile.getBytes());
		stream.close();
		return _file;
	}

}
