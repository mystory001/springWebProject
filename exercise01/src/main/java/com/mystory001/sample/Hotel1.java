package com.mystory001.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class Hotel1 {

	@Autowired // 객체 주입 방법 1 필드 주입
	private Chef chef;
	
}
