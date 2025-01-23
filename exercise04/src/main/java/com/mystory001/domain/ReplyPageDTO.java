package com.mystory001.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
@AllArgsConstructor
public class ReplyPageDTO {
	
	private int replyCnt;
	private List<ReplyVO> list;
	
}
