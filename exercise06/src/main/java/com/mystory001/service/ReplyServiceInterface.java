package com.mystory001.service;

import java.util.List;

import com.mystory001.domain.Criteria;
import com.mystory001.domain.ReplyPageDTO;
import com.mystory001.domain.ReplyVO;

public interface ReplyServiceInterface {

	public int insert(ReplyVO replyVO);
	
	public ReplyVO get(int rno);
	
	public int delete(int rno);
	
	public int update(ReplyVO replyVO);
	
	public List<ReplyVO> getListWithPaging(Criteria criteria, Integer bno);
	
	public ReplyPageDTO getListPage(Criteria criteria, Integer bno);
}
