package com.mystory001.mapper;

import java.util.List;

import com.mystory001.domain.BoardAttachVO;

public interface BoardAttachMapper {

	public void insert(BoardAttachVO attachVO);
	
	public void delete(String uuid);
	
	public List<BoardAttachVO> findByBno(Integer bno);
	
}
