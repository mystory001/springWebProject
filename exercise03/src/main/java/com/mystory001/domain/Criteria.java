package com.mystory001.domain;

import org.springframework.web.util.UriComponentsBuilder;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Criteria {

	private int pageNum; // 페이지 번호
	private int amount; // 한 페이지당 몇 개의 데이터를 보여줄 것인지

	public Criteria() {
		this(1,10);
	}
	
	public Criteria(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
	}

	private String type; // 검색 조건
	private String keyword;
	
	public String[] getTypeArr() {
		return type == null? new String[] {} : type.split("");
	}
	
	public String getListLink() {
		// UriComponentsBuilder 웹 페이지에서 매번 파라미터를 유지하는 일이 번거로울 때 이용하는 클래스
		UriComponentsBuilder builder = UriComponentsBuilder.fromPath("").queryParam("pageNum", this.pageNum)
																		.queryParam("amount", this.getAmount())
																		.queryParam("type", this.getType())
																		.queryParam("keyword", this.getKeyword());
		return builder.toString();
	}
	
}
