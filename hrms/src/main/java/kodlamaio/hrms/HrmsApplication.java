package kodlamaio.hrms;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;



import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import com.cloudinary.*;
import com.cloudinary.utils.ObjectUtils;


@SpringBootApplication
public class HrmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(HrmsApplication.class, args);
	}
	@Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()                                  
          .apis(RequestHandlerSelectors.basePackage("kodlamaio.northwind"))                                     
          .build();                                           
    }
	
	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper;
	}
	

}
