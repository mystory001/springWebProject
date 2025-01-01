package com.mystory001.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.Setter;

@Component // 스프링에게 해당 클래스가 스프링에서 관리해야 하는 대상임을 표시
@Data
public class Restaruant {

	@Setter(onMethod_ = @Autowired)// @Autowired : 필요한 의존 객체의 타입에 해당하는 빈을 찾아 주입
	private Chef chef;
	
}
