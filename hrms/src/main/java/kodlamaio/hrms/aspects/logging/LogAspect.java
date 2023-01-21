package kodlamaio.hrms.aspects.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import kodlamaio.hrms.core.utilities.results.Result;
import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class LogAspect {

	@Before("execution(* kodlamaio.hrms.business.concretes.*.add*(..))")
	public void logBefore(JoinPoint joinPoint) {
		log.info("{} çalıştı", joinPoint.getSignature());
	}

	@Pointcut("execution(* kodlamaio.hrms.business.concretes.*.add*(..))")
	public void pointcutForLogAfterReturning() {}
	
	@AfterReturning(pointcut = "pointcutForLogAfterReturning()", returning = "response")
	public void logAfterReturning(JoinPoint joinPoint, Result response) {

		log.info("{} bitti. {} ", joinPoint.getSignature(), response.getMessage());

	}

	@Pointcut("execution(* kodlamaio.hrms.business.conretes.*.*(..))")
	public void pointcutForLogAfterThrowing() {
	}

	@AfterThrowing(pointcut = "pointcutForLogAfterThrowing()", throwing = "exception")
	public void lofAfterThrowing(JoinPoint joinPoint, Throwable exception) {
		log.info("{} class'ı çalışırken hata: {} ", joinPoint.getClass().getSimpleName() ,exception.getMessage());
	}

}
