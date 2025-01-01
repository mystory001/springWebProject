package com.mystory001.sample;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor // 객체 주입 방법 4 스프링 버전 5.이상부터 가능 최근 가장 권장하는 방법
public class Hotel4 {

	private Chef chef;
	
}
