package com.mystory001.github;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) { // Controller의 메서드를 작성할 때는 Model이라는 타입을 파라미터로 지정. Model 객체는 JSP에 컨트롤러에서 생성된 데이터를 담아서 전달하는 역할을 하는 존재. 이를 이용해서 JSP와 같은 뷰로 전달해야 하는 데이터를 담아서 보낼 수 있음. 메서드의 파라미터에 Model 타입이 지정된 경우에는 스프링은 특별하게 Model 타입의 객체를 만들어서 메서드에 주입하게 됨
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		// Servlet에서 모델 2 방식으로 데이터를 전달하는 방식
		// request.setAttributte("serverTime", new java.util.Date());
		// RequestDsipatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/home.jsp");
		// dispatcher.forward(reqeust, response);
		
		// 스프링 MVC에서 Model을 이용한 데이터 전달
		model.addAttribute("serverTime", formattedDate );
		
		// Model을 사용해야하는 경우 1) 리스트 페이지 번호를 파라미터로 전달 받고, 실제 데이터를 View로 전달해야하는 경우, 2) 파라미터들에 대한 처리 후 결과를 전달해야 하는 경우
		
		return "home";
	}
	
}
