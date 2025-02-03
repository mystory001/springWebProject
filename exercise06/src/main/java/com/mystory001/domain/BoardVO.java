package com.mystory001.domain;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class BoardVO {
	
	private Integer bno, replyCnt;
	private String title, content, writer;
	private Date regdate, updatedate;

	private List<BoardAttachVO> attachList;
	
}
