package com.mystory001.aop;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j;

@Aspect // 해당 클래스의 객체가 Aspect를 구현한 것임으로 나타내기 위해 사용
@Log4j
@Component // 스프링에서 빈으로 인식하기 위해서 사용
public class LogAdvice { // 이전 프로젝트에서는 코드를 작성할 때 디버깅으로 log.info()를 이용해서 로그를 기록함 → 반복적이면서 핵심 로직은 아니지만 필요했음 → 로그를 기록해주는 클래스
	
	@Before("execution(* com.mystory001.service.SampleService*.*(..))") // @Before는 BeforeAdvice를 구현한 메서드에 추가, @After, @AfterReturning, @AfterThrowing, @Around 또한 동일한 방식으로 적용
																		// @Before 내부 문자열은 AspectJ 표현식, 앞의 *는 접근제한자를 의미, 뒤에 *는 클래스의 이름과 메서드의 이름
	public void logBefore() {
		log.info("==================================");
	}

	@Before("execution(* com.mystory001.service.SampleService*.doAdd(String, String)) && args(s1, s2)")
	public void logBeforeWithParam(String s1, String s2) { // doAdd() 메서드를 명시하고 파라미터 타입을 지정, &&args()에는 변수명을 지정하는데 2종류의 정보를 이용해서 logBeforeWithParm() 메서드의 파라미터를 설정. &&args를 이용하는 설정은 간단히 파라미터를 찾아서 기록할 때는 유용하지만 파라미터가 다른 여로 종류의 메서드에 적용하는 데 간단하지 않다는 단점을 가짐
		log.info("s1 : " + s1);
		log.info("s2 : " + s2);
	}
	
	// @AfterThrowing : 파라미터 값이 잘못되어 예외가 발생하는 경우가 많음. 지정된 대상이 예외를 발생한 후 동작하면서 문제를 찾을 수 있게 도와주는 어노테이션
	@AfterThrowing(pointcut = "execution(* com.mystory001.service.SampleService*.*(..))", throwing = "exception")
	public void logException(Exception exception) {
		log.info("Exception...............");
		log.info(exception);
	}
	
	// AOP를 이용해 좀 더 구체적인 처리를 하고 싶다면 @Around와 ProceedingJoinPoint를 이용해야함
	// @Around는 조금 특별하게 동작하는데 직접 대상 메서드를 실행할 수 있는 권한을 가지고 있고, 메서드의 실행 전과 후에 처리가 가능
	// ProceedingJoinPoint는 @Around와 같이 결합해서 파라미터나 예외 등을 처리할 수 있음
	@Around("execution(* com.mystory001.service.SampleService*.*(..))")
	public Object logTime(ProceedingJoinPoint proceedingJoinPoint) { // ProceedingJoinPoint 파라미터는 AOP의 대상이 되는 Target이나 파라미터 등을 파악하고, 직접 실행을 결정할 수 있음, @Before과 달리 @Around가 적용되는 메서드의 경우 리턴 타입이 void가 아닌 타입으로 설정하고, 메서드의 실행 결과 역시 직접 반환하는 형태로 작성해야함
		long start = System.currentTimeMillis();
		log.info("Target = " + proceedingJoinPoint.getTarget());
		log.info("Param = " + Arrays.toString(proceedingJoinPoint.getArgs()));
		
		// invoke method
		Object result = null;
		
		try {
			result = proceedingJoinPoint.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		long end = System.currentTimeMillis();
		
		log.info("Time = " + (end-start));
		
		return result;
	}
	
}
