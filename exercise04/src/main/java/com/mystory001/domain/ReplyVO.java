package com.mystory001.domain;

import java.util.Date;

import lombok.Data;

@Data
public class ReplyVO {
	
	private Integer rno, bno;
	private String reply, replyer;
	private Date replydate, updatedate;

}
