package com.mystory001.mapper;

import org.apache.ibatis.annotations.Select;

public interface TimeMapper { // Mapper SQL과 그에 대한 처리를 지정하는 역할. MyBatis-Srping을 이용하는 경우 Mapper를 XML과 인터페이스+어노테이션의 형태로 작성할 수 있음

	@Select("select sysdate from dual") // ;이 없어야함
	public String getTime();
	
	public String getTime2(); // Mapper인터페이스와 XML을 같이 이용해보기 위한 메서드
}
