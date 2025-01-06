package com.mystory001.exception;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import lombok.extern.log4j.Log4j;

@ControllerAdvice // AOP를 이용하는 방식, 핵심적인 로직은 아니지만 프로그램에서 필요한 공통적인 관심사는 분리하자는 개념. AOP 방식을 이용하면 공통적인 예외사항에 대해서는 별도로 @ControllerAdvice를 이용해서 분리하는 방식
@Log4j
public class CommonExceptionAdvice {
	
	// Controller의 예외 처리
	// Controller를 작성할 때 예외 상황을 고려하면 처리해야 하는 작업이 늘어날 수 밖에 없음 → 스프링 MVC에서는 이러한 작업을 1) @ExceptionHandler와 @ControllerAdvice를 이용한 처리 2) @ResponseEntity를 이용하는 예외 메시지 구성
	// @ControllerAdvice 해당 객체가 스프링의 컨트롤러에서 발생하는 예외를  처리하는 존재임을 명시하는 용도
	// @ExceptionHandlersms 해당 메서드가 () 들어가는 예외 타입을 처리한다는 것을 의미. 속성으로는 Exception 클래스 타입을 지정할 수 있음 
	
	@ExceptionHandler
	public String except(Exception exception, Model model) {
		log.error("Exception : " + exception.getMessage());
		model.addAttribute("exception", exception);
		log.error(model);
		return "error_page";
	}
	
	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String handler404(NoHandlerFoundException exception) {
		return "custom404";
	}

}
