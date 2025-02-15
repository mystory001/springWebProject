package com.mystory001.sample;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class) // 현재 테스트 코드가 스프링을 실행하는 역할을 할 것이라는 것을 표시
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml") // 지정된 클래스나 문자열을 이용해서 필요한 객체들을 스프링 내에 객체로
																				// 등록. 문자열은 classpath:나 file:을 이용. 자동으로
																				// 생성된 root-context.xml의 경로를 지정
@Log4j // lombok을 이용해서 로그를 기록하는 Logger를 변수로 생성. 별도의 Logger 객체 선언 없이 Log4j 라이브러리와 설정이
		// 존재한다면 바로 사용할 수 있음
public class SampleTest {

	@Setter(onMethod_ = { @Autowired })
	private Restaruant restaruant;

	@Setter(onMethod_ = { @Autowired })
	private Hotel1 hotel1;

	@Setter(onMethod_ = { @Autowired })
	private Hotel2 hotel2;

	@Setter(onMethod_ = { @Autowired })
	private Hotel3 hotel3;

	@Setter(onMethod_ = { @Autowired })
	private Hotel4 hotel4;

	@Test
	public void test1() {
		System.out.println("SampleTest test1()");
		assertNotNull(restaruant);
		log.info("SampleTest test1() log.info");
		log.info("restaruant : " + restaruant);
		log.info("restaruant.getChef() : " + restaruant.getChef());
		/*
		 * Console SampleTest test01() INFO : com.mystory001.sample.SampleTest -
		 * SampleTest test01() log.info INFO : com.mystory001.sample.SampleTest -
		 * restaruant : Restaruant(chef=Chef()) INFO : com.mystory001.sample.SampleTest
		 * - restaruant.getChef() : Chef() : new Restaurant()으로 Restaurant 클래스에서 객체를 생성한
		 * 적이 없는데 객체가 만들어짐 - 스프링은 관리가 필요한 객체(Bean)를 어노테이션 등을 이용해 객체를 생성하고 관리하는 컨테이너의 기능을
		 * 가지고 있음 Restaurant 클래스의 @Data 어노테이션으로 Lombok을 이용해서 여러 메서드가 만들어짐 Restaurant 객체의
		 * Chef 인스턴스 변수에 Chef 타입의 객체가 주입 - 스프링은 @Autowired와 같은 어노테이션을 이용해 개발자가 직접 객체들과의
		 * 관계를 관리하지 않고, 자동으로 관리되도록 함 ※ 이 테스트 결과가 의미하는 바는 1) 테스트 코드가 실행되기 위해서 스프링 프레임워크가
		 * 동작함 2) 동작하는 과정에서 필요한 객체들이 스프링에 등록 3) 의존성 주입에 필요한 객체는 자동으로 주입이 이루어짐
		 */
	}

	@Test
	public void testHotel1() {
		System.out.println("SampleTest testHotel1()");
		log.info(hotel1);
	}

	@Test
	public void testHotel2() {
		System.out.println("SampleTest testHotel2()");
		log.info(hotel2);
	}

	@Test
	public void testHotel3() {
		System.out.println("SampleTest testHotel3()");
		log.info(hotel3);
	}

	@Test
	public void testHotel4() {
		System.out.println("SampleTest testHotel3()");
		log.info(hotel4);
	}

}
