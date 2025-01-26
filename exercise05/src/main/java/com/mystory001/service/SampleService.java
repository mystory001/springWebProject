package com.mystory001.service;

import org.springframework.stereotype.Service;

@Service
public class SampleService implements SampleServiceInterface{

	@Override
	public Integer doAdd(String s1, String s2) throws Exception { // 단순히 문자열을 변환해서 더하기 연산을 하는 단순 작업
		return Integer.parseInt(s1) + Integer.parseInt(s2);
	}

}
