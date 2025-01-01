package com.mystory001.sample;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;


@Component
@AllArgsConstructor // 객체 주입 방법 3 생성자 주입
public class Hotel3 {

	private Chef chef;
}
