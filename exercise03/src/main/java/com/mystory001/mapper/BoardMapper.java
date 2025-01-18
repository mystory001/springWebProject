package com.mystory001.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.mystory001.domain.BoardVO;
import com.mystory001.domain.Criteria;

public interface BoardMapper {
	
	public List<BoardVO> getList();

	public List<BoardVO> getListWithPaging(Criteria criteria);
	
	public void insert(BoardVO boardVO);
	
	public void insertSelectKey(BoardVO boardVO);
	
	public BoardVO get(Integer bno);
	
	public int delete(Integer bno);
	
	public int update(BoardVO boardVO);

}
