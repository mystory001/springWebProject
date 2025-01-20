package com.mystory001.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mystory001.domain.People;
import com.mystory001.domain.SampleVO;

@RestController // JSP와 달리 순수한 데이터를 반환하는 형태이므로 다양한 포멧의 데이터를 전송할 수 있음 → 주로 많이 사용하는 형태는 일반 문자열, JSON, XML 등
@RequestMapping("/sample")
public class SampleController {
	
	// 단순 문자열 반환
	@GetMapping(value = "/getText", produces = "text/plain;charset=UTF-8") // produces 속성은 해당 메서드가 생산하는 MIME 타입을 의미
	public String getText() {
		System.out.println("SampleController getTest()...............");
		System.out.println("MIME TYPE : " + MediaType.TEXT_PLAIN_VALUE );
		return "안녕하세요.";
	}

	// 객체의 반환
	@GetMapping(value = "/getObj", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE,
													  MediaType.APPLICATION_XML_VALUE}) 
	public SampleVO getObj() {
		System.out.println("SampleController getObj()...............");
		return new SampleVO(1,"홍","길동");
		// http://localhost:8080/sample/getObj :
		// <SampleVO>
		// <bno>1</bno>
		// <firstName>홍</firstName>
		// <lastName>길동</lastName>
		// </SampleVO>
		// http://localhost:8080/sample/getObj.json : {"bno":1,"firstName":"홍","lastName":"길동"}
	}
	
	// 컬렉션 타입(배열, 리스트, 맵)의 객체 반환 1
	@GetMapping(value= "/getList")
	public List<SampleVO> getList(){
		System.out.println("SampleController getList()...............");
		return IntStream.range(1, 10).mapToObj(i -> new SampleVO(i,"First " + i, "Last " + i )).collect(Collectors.toList());
	}
	
	// 컬렉션 타입(배열, 리스트, 맵)의 객체 반환 2
	@GetMapping(value = "/getMap")
	public Map<String, SampleVO> getMap(){
		System.out.println("SampleController getMap()...............");
		Map<String, SampleVO> map = new HashMap();
		map.put("홍길동", new SampleVO(1,"홍","길동")); // Map을 이용하는 경우에는 키에 속하는 데이터는 XML로 변환되는 경우에 태그의 이름이 되기 떄문에 문자열을 지정
		return map;
	}
	
	// ResponseEntity 타입 : REST 방식으로 호출하는 경우 화면 자체가 아니라 데이터 자체를 전동하는 방식으로 처리되기 때문에 데이터를 요청한 쪽에서는 정상적인 데이터인지 아닌지를 구분할 수 있는 확실한 방법을 제공 → ResponseEntity는 데이터와 함께 HTTP 헤더의 상태 메시지 등을 같이 전달하는 용도로 사용
	@GetMapping(value = "/getResponseEntity", params = {"height","weight"})
	public ResponseEntity<SampleVO> getResponseEntity(Double height, Double weight){
		System.out.println("SampleController getResponseEntity()...............");
		SampleVO sampleVO = new SampleVO(0, height+"", weight+"");
		ResponseEntity<SampleVO> result = null;
		
		if(height < 10 &&weight < 5) {
			result = ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(sampleVO);
		} else {
			result = ResponseEntity.status(HttpStatus.OK).body(sampleVO);
		}
		return result;
		// http://localhost:8080/sample/getResponseEntity?height=11&weight=6 → 200(OK)
		// http://localhost:8080/sample/getResponseEntity?height=9&weight=4 → 502(BAD_GATEWAY)
	}
	
	// @PathVariable
	@GetMapping("product/{category}/{pid}")
	public String[] getPathVariable(@PathVariable("category") String category, @PathVariable("pid") Integer pid) {
		System.out.println("SampleController getPathVariable()...............");
		return new String[] {"category : " + category, "productid : " + pid};
		// http://localhost:8080/sample/product/laptop/1234
	}
	
	// @RequestBody → 요청(request)한 내용(body)을 처리하기 때문에 파라미터 전달방식을 사용할 수 없기 때문에 PostMapping을 사용해야함 → URI로 접속 불가
	@PostMapping("/people")
	public People people(@RequestBody People people) {
		System.out.println("SampleController people()...............");
		System.out.println("convert people : " + people);
		return people;
	}
	
	
}
