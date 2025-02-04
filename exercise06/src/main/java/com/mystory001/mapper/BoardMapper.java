package com.mystory001.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.mystory001.domain.BoardVO;
import com.mystory001.domain.Criteria;

public interface BoardMapper {
	
	public List<BoardVO> getList();

	public List<BoardVO> getListWithPaging(Criteria criteria);
	
	public void insert(BoardVO boardVO);
	
	public Integer insertSelectKey(BoardVO boardVO); 
	
	public BoardVO get(Integer bno); 
	
	public int delete(Integer bno); 
	
	public int update(BoardVO boardVO); 
	
	public int getTotalCount(Criteria criteria); // 전체 데이터의 개수 처리
	
	public void updateReplyCnt(@Param("bno") Integer bno, @Param("amount") Integer amount); // 해당 게시물의 번호 bno와 증감을 의미

}
