package com.mystory001.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class SampleServiceTests {

	@Setter(onMethod_ = {@Autowired})
	private SampleServiceInterface sampleService;
	
	@Test
	public void testClass() { // AOP 설정을 한 Target에 대해서 Proxy 객체가 정상적으로 만들어져 있는지 확인 → root-context.xml에서 autoproxy가 정상적으로 동작하고 LogAdvice에 설정 문제가 없다면 service 변수의 클래스는 Proxy클래스의 인스턴스가 생성
		log.info(sampleService);
		log.info(sampleService.getClass().getName());
	}
	
	@Test
	public void testAdd() throws Exception{
		log.info(sampleService.doAdd("123", "456"));
	}
	
	@Test 
	public void testAddError() throws Exception{
		log.info(sampleService.doAdd("123", "DEF"));
	}
	
	
}
