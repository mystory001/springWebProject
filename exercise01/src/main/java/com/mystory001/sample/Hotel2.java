package com.mystory001.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Setter;

@Component
public class Hotel2 {

	@Setter(onMethod_ = {@Autowired})// 객체 주입 방법 2 Setter 주입
	private Chef chef;
	
}
