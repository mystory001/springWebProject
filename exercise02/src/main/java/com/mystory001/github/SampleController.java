package com.mystory001.github;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

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
	
	@GetMapping("/test07")
	public String test07(SampleDTO sampleDTO, int page) {
		log.info("sampleDTO : " + sampleDTO);
		log.info("page : " + page);
		return "/sample/test07";
	}

	@GetMapping("/test08")
	public String test08(SampleDTO sampleDTO, @ModelAttribute("page") int page) { // @ModelAttribute는 강제로 전달받은 파라미터를 Model에 담아서 전달하도록 할 때 필요한 어노테이션. 타입에 관계없이 무조건 Model에 담아서 전달되므로, 파라미터로 전달된 데이터를 다시 화면에서 사용해야 할 경우 유용하게 사용. 반드시 값을 지정
		log.info("sampleDTO : " + sampleDTO);
		log.info("page : " + page);
		return "/sample/test08";
	}
	
	// Controller 리턴 타입 : 스프링 MVC의 구조가 기존의 상속과 인터페이스에서 어노테이션을 사용하는 방식으로 변한 이후 가장 큰 변화는 리턴 타입이 자유로워짐
	// String : jsp를 이용하는 경우 jsp 파일의 경로와 파일이름을 나타내기 위해서 사용
	// void : 호출하는 URL과 동일한 이름의 jsp를 의미
	// VO 또는 DTO 타입 : JSON 타입의 데이터를 만들어서 반환하는 용도로 사용
	// ResponseEntity 타입 : response할 때 Http 헤더 정보와 내용을 가공하는 용도로 사용
	
	// String 타입 : 상황에 따라 다른 화면을 보여줄 필요가 있는 경우(if~else), 현재 프로젝트의 경우 JSP 파일의 이름을 의미

	// void 타입 : 메서드의 리턴 타입을 void로 지정하는 경우 일반적인 경우에 해당 URL의 경로를 그대로 jsp 파일의 이름으로 사용하게 됨
	// http://localhost:8080/sample/test09 → INFO : com.mystory001.github.SampleController - /test09()
	@GetMapping("/test09")
	public void test09() {
		log.info("/test09()");
	}
	
	// 객체 타입 : Controller의 메서드 리턴 타입을 VO나 DTO 타입 등 복합적인 데이터가 들어간 객체 타입으로 지정할 수 있는데 이 경우 주로 JSON 데이터를 만들어 내는 용도로 사용 → 이를 위해서는 jackson-databind 라이브러리를 추가
	@GetMapping("/test10")
	public @ResponseBody SampleDTO test10() {
		log.info("/test10()");
		SampleDTO sampleDTO = new SampleDTO();
		sampleDTO.setAge(20);
		sampleDTO.setName("홍길동");
		
		return sampleDTO; // {"name":"홍길동","age":20} → Response Headers : Content-Type: application/json;charset=UTF-8
	}
	
	// ResponseEntitiy 타입 : HTTP 프로토콜의 헤더를 다루는 경우가 종종 있음. HttpServletRequest나 HttpServletResponse를 직접 핸들링하지 않아도 이런 작업이 가능하도록 되었기 때문에 이러한 처리를 위해 ResponseEntitiy를 통해서 원하는 헤더 정보나 데이터를 전달
	@GetMapping("/test11")
	public ResponseEntity<String> test11(){
		log.info("/test11()");

		String msg = "{\"name\": \"홍길동\"}";
		HttpHeaders header = new HttpHeaders(); // org.springframework.http.HttpHeaders
		header.add("Content-Type", "application/json;charset=UTF-8");
		
		return new ResponseEntity<String>(msg,header,HttpStatus.OK); // {"name": "홍길동"}
	}
	// ResponseEntity는 HttpHeaders 객체를 같이 전달할 수 있고, 이를 통해 원하는 HTTP 헤더 메시지를 가공하는 것이 가능
	
	@GetMapping("/testUpload")
	public void testUpload() {
		log.info("/testUpload()");
	}
	
	@PostMapping("/testUploadPost")
	public void testUploadPost(ArrayList<MultipartFile> files) { // 스프링 MVC는 전달되는 파라미터가 동일한 이름으로 여러 개 존재하면 배열로 처리가 가능
		for(int i = 0; i < files.size(); i++) {
			log.info("=============");
			log.info("name : " + files.get(i).getOriginalFilename());
			log.info("size : " + files.get(i).getSize());
		}
	}
}
