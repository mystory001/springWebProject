package com.mystory001.persistence;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class DataSourceTests {
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	@Test
	public void testConnection() {
		try (Connection connection = dataSource.getConnection()) {
			log.info(connection);
		} catch(Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}
	}
	
	@Test
	public void testConnection1() throws Exception {
		
		Class cla = Class.forName("oracle.jdbc.driver.OracleDriver");
		long startTime = System.currentTimeMillis();
		
		for(int i = 0; i < 10; i++) {
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","book_ex","book_ex");
		log.info(connection);
		connection.close();
		}
		
		long endTime = System.currentTimeMillis();
		
		log.info("10번 연결되는데 소요된 시간 : " + (endTime - startTime));
	}

	@Test
	public void testMyBatis() {
		try(SqlSession session = sqlSessionFactory.openSession(); Connection connection = session.getConnection()){
			log.info(session);
			log.info(connection);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
