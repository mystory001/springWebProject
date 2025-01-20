package com.mystory001.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor // 비어 있는 생성자를 만들기 위한 어노테이션
public class SampleVO {
	
	private Integer bno;
	private String firstName;
	private String lastName;

}
