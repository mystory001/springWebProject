package com.mystory001.domain;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data // getter/setter, equals(), toString() 등의 메서드를 자동으로 생성
public class SampleDTOList {
	
	private List<SampleDTO> list;
	
	public SampleDTOList() {
		list = new ArrayList<SampleDTO>();
	}

}
