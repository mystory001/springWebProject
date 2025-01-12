package com.mystory001.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


import lombok.Setter;
import lombok.extern.log4j.Log4j;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml"
					  ,"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
})
@Log4j
public class BoardControllerTests {
	
	@Setter(onMethod_ = {@Autowired})
	private WebApplicationContext applicationContext;
	
	private MockMvc mockMvc;
	
	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(applicationContext).build();
	}
	
	@Test
	public void testList() throws Exception{
		log.info(mockMvc.perform(MockMvcRequestBuilders.get("/board/list")).andReturn().getModelAndView().getModelMap());
	}

	@Test
	public void testInsert() throws Exception{
		log.info("BoardControllerTests testInsert().............................");
		log.info(mockMvc.perform(MockMvcRequestBuilders.post("/board/insert")
																  .param("title", "테스트250112")
																  .param("content", "테스트250112")
																  .param("writer", "테스트250112")
																  ).andReturn().getModelAndView().getViewName());
	}
	
	@Test
	public void testGet() throws Exception{
		log.info(mockMvc.perform(MockMvcRequestBuilders.get("/board/get")
				  .param("bno", "1")
				  ).andReturn().getModelAndView().getModelMap());
	}

	@Test
	public void testUpdate() throws Exception{
		log.info(mockMvc.perform(MockMvcRequestBuilders.post("/board/update")
				.param("bno", "5")
				.param("title", "testUpdate")
				.param("content", "testUpdate")
				.param("writer", "testUpdate")
				).andReturn().getModelAndView().getViewName());
	}
	
	@Test
	public void testDelete() throws Exception{
		log.info(mockMvc.perform(MockMvcRequestBuilders.post("/board/delete")
				.param("bno", "5")
				).andReturn().getModelAndView().getViewName());
	}
	
	

}
