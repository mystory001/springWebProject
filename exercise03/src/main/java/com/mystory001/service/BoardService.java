package com.mystory001.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mystory001.domain.BoardVO;
import com.mystory001.domain.Criteria;
import com.mystory001.mapper.BoardMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@Service // 계층 구조상 주로 비즈니스 영역을 담당하는 객체임을 표시하기 위해 사용
@AllArgsConstructor
public class BoardService implements BoardSerivceInterface{

	private BoardMapper boardMapper;
	
	@Override
	public BoardVO get(Integer bno) {
		log.info("BoardService get()...............");
		return boardMapper.get(bno);
	}

	@Override
	public int insert(BoardVO boardVO) {
		log.info("BoardService insert()...............");
		boardMapper.insertSelectKey(boardVO);
		return boardVO.getBno();
	}

	@Override
	public int update(BoardVO boardVO) {
		log.info("BoardService update()...............");
		return boardMapper.update(boardVO);
	}

	@Override
	public int delete(Integer bno) {
		log.info("BoardService update()...............");
		return boardMapper.delete(bno);
	}

	@Override
	public List<BoardVO> getList(Criteria criteria) {
		log.info("BoardService getList()...............");
		log.info("critera : " + criteria);
		return boardMapper.getListWithPaging(criteria);
	}

}
