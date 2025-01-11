package com.mystory001.service;

import java.util.List;

import com.mystory001.domain.BoardVO;

public interface BoardSerivceInterface {

	public BoardVO get(Integer bno);

	public void insert(BoardVO boardVO);
	
	public void update(BoardVO boardVO);
	
	public void delete(Integer bno);
	
	public List<BoardVO> getList();
	
}
