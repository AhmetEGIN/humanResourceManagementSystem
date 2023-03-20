package kodlamaio.hrms.core.handleExceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import kodlamaio.hrms.core.utilities.results.ErrorDataResult;

@RestControllerAdvice
public class GlobalExceptionHandler {
	// ControllerAdvice sınıfları, uygulamamızdaki birden fazla veya tüm controller için exception handler yazmamıza izin verir.
	// 'Advice' terimi, mevcut metodların etrafına cross-cutting kodu ("advice" olarak adlandırılır) enjekte etmemizi sağlayan Aspect-Oriented Programlama (AOP)'dan gelir. 
	// Bir bakıma bu da bir Interceptor dır.
	// RestControllerAdvice ise Rest uygulamalarında kısa ve kullanışlıdır.
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorDataResult<Object> handleValidationException(MethodArgumentNotValidException exceptions) {
		Map<String, String> validationErrors = new HashMap<String, String>();
		for (FieldError fieldError : exceptions.getBindingResult().getFieldErrors()) {
			validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
		}
		ErrorDataResult<Object> errors = new ErrorDataResult<Object>(validationErrors, "Doğrulama hataları");
		return errors;
	}
}
