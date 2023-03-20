package kodlamaio.hrms;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import lombok.extern.slf4j.Slf4j;



@SpringBootApplication
@Slf4j
public class HrmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(HrmsApplication.class, args);
		log.info("System started");
	}
	
//	@Bean
//    public Docket api() { 
//        return new Docket(DocumentationType.SWAGGER_2)  
//          .select()                                  
//          .apis(RequestHandlerSelectors.basePackage("kodlamaio.northwind"))     
//          .paths(PathSelectors.ant("/**"))
//          .build();
//
//    }
	
	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper;
	}
	

}
