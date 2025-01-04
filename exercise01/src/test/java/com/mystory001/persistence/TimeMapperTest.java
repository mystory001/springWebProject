package com.mystory001.persistence;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mystory001.mapper.TimeMapper;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class TimeMapperTest { // 건너뛰지 않고 무조건 해봐야함

	@Autowired
	private TimeMapper timeMapper;
	
	@Test
	public void testTime1() {
		log.info(timeMapper.getTime());
	}
	
	@Test
	public void testTime2() {
		log.info(log);
		log.info(timeMapper.getTime2());
	}
	
}
