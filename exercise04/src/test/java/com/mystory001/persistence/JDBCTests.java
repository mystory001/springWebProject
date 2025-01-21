package com.mystory001.persistence;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

import lombok.extern.log4j.Log4j;

@Log4j
public class JDBCTests {
	
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
		
		log.info(end-start);
	}

}
