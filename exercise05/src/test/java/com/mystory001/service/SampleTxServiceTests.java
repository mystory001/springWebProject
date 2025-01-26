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
public class SampleTxServiceTests {
	
	@Setter(onMethod_  = {@Autowired})
	private SampleTxServiceInterface sampleTxService;
	
	@Test
	public void testLong() {
		String str ="abcdefghijklmnopqrstuvwxyz\r\n" + "ㄱㄷㄷㄻㅄ\r\n"+"12345687879023748590782349507891287093";
		
		log.info(str.getBytes().length);
		
		sampleTxService.addData(str);
	}
	

}
