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
public class DataSourceTest {
	
	@Autowired
	private DataSource dataSource;
	
	@Test
	public void testConnection() {
		try (Connection connection = dataSource.getConnection()) {
			log.info(connection);
		} catch(Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}
	}
	
	/*
	INFO : org.springframework.test.context.support.DefaultTestContextBootstrapper - Loaded default TestExecutionListener class names from location [META-INF/spring.factories]: [org.springframework.test.context.web.ServletTestExecutionListener, org.springframework.test.context.support.DirtiesContextBeforeModesTestExecutionListener, org.springframework.test.context.support.DependencyInjectionTestExecutionListener, org.springframework.test.context.support.DirtiesContextTestExecutionListener, org.springframework.test.context.transaction.TransactionalTestExecutionListener, org.springframework.test.context.jdbc.SqlScriptsTestExecutionListener]
	INFO : org.springframework.test.context.support.DefaultTestContextBootstrapper - Using TestExecutionListeners: [org.springframework.test.context.web.ServletTestExecutionListener@1ff4931d, org.springframework.test.context.support.DirtiesContextBeforeModesTestExecutionListener@65e98b1c, org.springframework.test.context.support.DependencyInjectionTestExecutionListener@61322f9d, org.springframework.test.context.support.DirtiesContextTestExecutionListener@6ad82709, org.springframework.test.context.transaction.TransactionalTestExecutionListener@510f3d34, org.springframework.test.context.jdbc.SqlScriptsTestExecutionListener@7817fd62]
	INFO : org.springframework.beans.factory.xml.XmlBeanDefinitionReader - Loading XML bean definitions from URL [file:src/main/webapp/WEB-INF/spring/root-context.xml]
	INFO : org.springframework.context.support.GenericApplicationContext - Refreshing org.springframework.context.support.GenericApplicationContext@186f8716: startup date [Sat Jan 04 16:05:13 KST 2025]; root of context hierarchy
	INFO : org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor - JSR-330 'javax.inject.Inject' annotation found and supported for autowiring
	INFO : com.zaxxer.hikari.HikariDataSource - HikariPool-1 - Starting...
	WARN : com.zaxxer.hikari.util.DriverDataSource - Registered driver with driverClassName=oracle.jdbc.driver.OracleDriver was not found, trying direct instantiation.
	INFO : com.zaxxer.hikari.pool.PoolBase - HikariPool-1 - Driver does not support get/set network timeout for connections. (Receiver class oracle.jdbc.driver.T4CConnection does not define or inherit an implementation of the resolved method 'abstract int getNetworkTimeout()' of interface java.sql.Connection.)
	INFO : com.zaxxer.hikari.HikariDataSource - HikariPool-1 - Start completed.
	INFO : com.mystory001.persistence.DataSourceTest - HikariProxyConnection@1075996552 wrapping oracle.jdbc.driver.T4CConnection@aa5455e
	INFO : org.springframework.context.support.GenericApplicationContext - Closing org.springframework.context.support.GenericApplicationContext@186f8716: startup date [Sat Jan 04 16:05:13 KST 2025]; root of context hierarchy
	INFO : com.zaxxer.hikari.HikariDataSource - HikariPool-1 - Shutdown initiated...
	INFO : com.zaxxer.hikari.HikariDataSource - HikariPool-1 - Shutdown completed.
	 */

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
		
		log.info(endTime-startTime); // 커넥션풀을 이용한 시간 94(커넥션풀이 JDBC보다 2배 이상 빠름)
		// DB와 연결? 커넥션풀을 사용(속도, 안정성면에서 JDBC보다 훨씬 좋음)
	}
	
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
	//pom.xml 라이브러리 추가, root-context.xml에 bean 추가
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
