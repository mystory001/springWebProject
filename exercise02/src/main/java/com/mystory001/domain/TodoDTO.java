package com.mystory001.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class TodoDTO {

	private String title;
	
	//@DateTimeFormat : 파라미터로 사용되는 인스턴스 변수에 적용 변환 가능. 이 어노테이션을 이용하는 경우 @InitBinder는 필요하지 않음
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private Date dueDate;
	
}
