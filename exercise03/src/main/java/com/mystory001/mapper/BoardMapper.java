package com.mystory001.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.mystory001.domain.BoardVO;

public interface BoardMapper {
	
	public List<BoardVO> getList();
	
	public void insert(BoardVO boardVO);
	
	public void insertSelectKey(BoardVO boardVO);
	
	public BoardVO get(Integer bno);
	
	public void delete(Integer bno);
	
	public void update(BoardVO boardVO);

}
