package com.mystory001.mapper;

import org.apache.ibatis.annotations.Insert;

public interface Sample1Mapper {
	
	@Insert("insert into sample1(col1) values(#{date})")
	public int insertCol1(String data);

}
