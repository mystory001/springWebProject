package com.mystory001.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.google.gson.Gson;
import com.mystory001.domain.People;

import lombok.Setter;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml"
					  ,"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
})
public class SampleControllerTests {
	
	@Setter(onMethod_ = {@Autowired})
	private WebApplicationContext wcc;
	
	private MockMvc mock;
	
	@Before
	public void setUp() {
		this.mock = MockMvcBuilders.webAppContextSetup(wcc).build();
	}
	
	@Test
	public void testPeople() throws Exception{
		People p = new People();
		p.setAge(10);
		p.setName("스프링");
		p.setJob("학생");
		
		String jsonStr = new Gson().toJson(p);
		System.out.println("jsonStr : " + jsonStr);
		
		mock.perform(post("sample/people").contentType(MediaType.APPLICATION_JSON).content(jsonStr)).andExpect(status().is(200));
	}

}
