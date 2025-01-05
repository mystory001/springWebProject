package com.mystory001.github;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mystory001.domain.SampleDTO;
import com.mystory001.domain.SampleDTOList;
import com.mystory001.domain.TodoDTO;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/sample/*") // 현재 클래스의 모든 메서드들의 기본적인 URL 경로
@Log4j
public class SampleController {
	
	@RequestMapping("")
	public void basic() {
		log.info("SampleController basic()");
	}
	
	//스프링 4.3 버전부터 @RequestMapping을 줄여서 @GetMapping(Get방식), @PostMapping(Post방식)
	@RequestMapping(value = "/basic", method = { RequestMethod.GET, RequestMethod.POST })
	public void basicGet() {
		log.info("SampleController basicGet()");
	}
	
	@GetMapping("/basic2")
	public void basicGet2() {
		log.info("SampleController basicGet2()");
	}

	// @RequestMapping은 GET, POST 방식 모두 지원해야 하는 경우 배열로 처리해서 지정할 수 있음. 일반적인 경우에는 GET, POST 방식만을 사용하지만, PUT, DELETE 방식 등도 많이 사용
	// @GetMapping이나 @PostMapping의 경우 오직 GET, POST 방식에만 사용할 수 있음. 간편하지만 기능에 대한 제한이 많은 편

	// http://localhost:8080/sample/test01?name=AAA&age=20
	@GetMapping("/test01")
	public String test01(SampleDTO sampleDTO) {
		log.info("" + sampleDTO);
		return "test01";
	}
	// INFO : com.mystory001.github.SampleController - SampleDTO(name=AAA, age=20)
	// 자동으로 타입을 변환해서 처리
	
	// 파라미터의 수집과 변환 : Controller가 파라미터를 수집하는 방식은 파라미터 타입에 따라 자동으로 변환하는 방식을 이용
	// http://localhost:8080/sample/test02?name=AAA&age=20
	@GetMapping("/test02")
	public String test02(@RequestParam("name") String name, @RequestParam("age") int age) {
		log.info("name : " + name);
		log.info("age : " + age);
		return "test02";
	}
	// INFO : com.mystory001.github.SampleController - name : AAA
	// INFO : com.mystory001.github.SampleController - age : 20
	// 기본 자료형이나 문자열 등을 이용한다면 파라미터의 타입만을 맞게 선언해주는 방식을 사용
	// @RequestParam : 파라미터로 사용된 변수의 이름과 전달되는 파라미터의 이름이 다른 경우 유용하게 사용
	
	// 리스트, 배열 처리 : 동일한 이름의 파라미터가 여러 개 전달되는 경우 ArrayList<>를 이용해서 처리가 가능
	// http://localhost:8080/sample/test03?id=1&id=2&id=3
	@GetMapping("/test03")
	public String test03(@RequestParam("id") ArrayList<String> id) {
		log.info("id : " + id);
		return "test03";
	}
	// INFO : com.mystory001.github.SampleController - ids : [1, 2, 3]
	// 스프링은 파라미터의 타입을 보고 객체를 생성하므로 파라미터의 타입은 List와 같이 인터페이스 타입이 아닌 실제적인 클래스 타입으로 지정
	
	// 배열로 처리
	// http://localhost:8080/sample/test04?id=1&id=2&id=3
	@GetMapping("/test04")
	public String test04(@RequestParam("id") String[] id) {
		log.info("array id : " + Arrays.toString(id));
		return "test04";
	}	
	// INFO : com.mystory001.github.SampleController - array id : [1, 2, 3]
	
	// 객체 리스트
	@GetMapping("/test05")
	public String test05(SampleDTOList sampleDTOList) {
		log.info("sampleDTOList DTOs : " + sampleDTOList);
		return "test05";
	}	
	// http://localhost:8080/sample/test05?list[0].name=AAA&list[2].name=BBBB → '['와 ']' 문자를 특수문자로 허용하지 않을 수 있음 → [ : %5B, ] : %5D로 변경
	// http://localhost:8080/sample/test05?list%5B0%5D.name=AAA&list%5B2%5D.name=BBBB
	// INFO : com.mystory001.github.SampleController - sampleDTOList DTOs : SampleDTOList(list=[SampleDTO(name=AAA, age=0), SampleDTO(name=null, age=0), SampleDTO(name=BBBB, age=0)])
	
	// @InitBinder
	// 파라미터의 수집을 다른 용어로 binding(바인딩)이라고 함. 변환이 가능한 데이터는 자동으로 변환되지만, 경우에 따라서는 파라미터를 변환해서 처리해야 하는 경우도 존재
	// 예를 들어 2025-01-01 문자열로 전달된 데이터를 java.util.Date 타입으로 변환하는 작업
	// 스프링 Controller에서는 파라미터를 바인딩할 때 자동으로 호출되는 @InitBinder를 이용해서 변환 처리가 가능
//	@InitBinder
//	public void initBinder(WebDataBinder binder) {
//		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//		binder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(dateFormat, false));
//	}
	// http://localhost:8080/sample/test06?title=title&dueDate=2025-01-01
	@GetMapping("/test06")
	public String test06(TodoDTO todoDTO) {
		log.info("todoDTO : " + todoDTO);
		return "test06";
	}
	// INFO : com.mystory001.github.SampleController - todoDTO : TodoDTO(title=title, dueDate=Wed Jan 01 00:00:00 KST 2025)
	
	// @DateTimeFormat : 파라미터로 사용되는 인스턴스 변수에 적용 변환 가능. 이 어노테이션을 이용하는 경우 @InitBinder는 필요하지 않음
	// http://localhost:8080/sample/test06?title=title&dueDate=2025/01/01
	// INFO : com.mystory001.github.SampleController - todoDTO : TodoDTO(title=title, dueDate=Wed Jan 01 00:00:00 KST 2025)
	
}
