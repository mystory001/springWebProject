package com.mystory001.persistence;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

import lombok.extern.log4j.Log4j;

@Log4j
public class JDBCTest { // JDBC 드라이버가 정상적으로 추가되었고, 데이터베이스의 연결이 가능하다면 이를 눈으로 확인할 수 있게 테스트 코드를 작성

	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void testConnection() {
		long start = System.currentTimeMillis();
		for(int i = 0; i < 10; i++) {
			try(Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "book_ex", "book_ex")){
				log.info(connection);
				connection.close();
			} catch(Exception e){
				e.printStackTrace();
			} 
		}
		long end = System.currentTimeMillis();
		
		log.info(end-start); // JDBC을 이용한 시간 213
	}
	// JUnit Test 결과 
	// INFO : com.mystory001.persistence.JDBCTest - oracle.jdbc.driver.T4CConnection@121314f7
	
}
