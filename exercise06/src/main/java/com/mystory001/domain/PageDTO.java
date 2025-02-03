package com.mystory001.domain;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PageDTO {
			  
	private int startPage; // 화면에 보여지는 페이지의 시작 번호
	private int endPage; // // 화면에 보여지는 페이지의 끝 번호
	private boolean prev, next; // 이전, 다음
	
	private int total; // 전체 데이터 수
	private Criteria criteria;
	
	public PageDTO(Criteria criteria, int total) {
		this.criteria = criteria;
		this.total = total;
		
		this.endPage = (int)(Math.ceil(criteria.getPageNum()/10.0)) * 10;
		this.startPage = this.endPage - 9;
		
		int realEnd = (int)(Math.ceil((total * 1.0) / criteria.getAmount()));
		
		if(realEnd < this.endPage) {
			this.endPage = realEnd;
		}
		
		this.prev = this.startPage > 1;
		
		this.next = this.endPage < realEnd;
	}
	
	
}
