package com.mystory001.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mystory001.domain.Criteria;
import com.mystory001.domain.ReplyVO;

public interface ReplyMapper { // 댓글에 대한 처리는 화면상에서 페이지 처리가 필요 → Criteria를 이용해서 처리
	
	public int insert(ReplyVO replyVO);
	
	public ReplyVO get(int rno); // 조회
	
	public int delete(int rno);
	
	public int update(ReplyVO replyVO);
	
	public List<ReplyVO> getListWithPaging(@Param("criteria") Criteria criteria,@Param("bno") Integer bno);
	
	public int getCountByBno(int bno);
	
}
