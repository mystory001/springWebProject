package com.mystory001.service;

import java.util.List;

import com.mystory001.domain.BoardVO;

public interface BoardSerivceInterface {

	public BoardVO get(Integer bno);

	public int insert(BoardVO boardVO);
	
	public int update(BoardVO boardVO);
	
	public int delete(Integer bno);
	
	public List<BoardVO> getList();
	
}
