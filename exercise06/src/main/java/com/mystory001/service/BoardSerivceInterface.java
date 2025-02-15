package com.mystory001.service;

import java.util.List;

import com.mystory001.domain.BoardAttachVO;
import com.mystory001.domain.BoardVO;
import com.mystory001.domain.Criteria;

public interface BoardSerivceInterface {

	public BoardVO get(Integer bno);

	public void insert(BoardVO boardVO);
	
	public boolean update(BoardVO boardVO);
	
	public boolean delete(Integer bno);
	
//	public List<BoardVO> getList();

	public List<BoardVO> getList(Criteria criteria);
	
	public int getTotalCount(Criteria criteria);
	
	public List<BoardAttachVO> getAttachList(Integer bno);
	
	public void deleteAttach(Integer bno);
	
}
