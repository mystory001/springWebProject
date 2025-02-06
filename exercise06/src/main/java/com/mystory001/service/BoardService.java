package com.mystory001.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mystory001.domain.BoardAttachVO;
import com.mystory001.domain.BoardVO;
import com.mystory001.domain.Criteria;
import com.mystory001.mapper.BoardAttachMapper;
import com.mystory001.mapper.BoardMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@Service // 계층 구조상 주로 비즈니스 영역을 담당하는 객체임을 표시하기 위해 사용
@AllArgsConstructor
public class BoardService implements BoardSerivceInterface{
	
	private BoardMapper boardMapper;

	private BoardAttachMapper  attachMapper;
	
	@Override
	public BoardVO get(Integer bno) {
		log.info("BoardService get()...............");
		return boardMapper.get(bno);
	}
	
	@Transactional
	@Override
	public void insert(BoardVO boardVO) {
		log.info("BoardService insert()...............");
		boardMapper.insertSelectKey(boardVO);
		
		if(boardVO.getAttachList() == null || boardVO.getAttachList().size() <= 0) {
			return;
		}
		
		boardVO.getAttachList().forEach(attach -> {
												   attach.setBno(boardVO.getBno()); 
												   attachMapper.insert(attach);
												   });
	}

	@Transactional
	@Override
	public boolean update(BoardVO boardVO) {
		log.info("BoardService update()...............");
		log.info("boardVO : " + boardVO);
		
		attachMapper.deleteAll(boardVO.getBno());
		
		boolean updateResult = boardMapper.update(boardVO) == 1;
		
		if(updateResult && boardVO.getAttachList() != null || boardVO.getAttachList().size() > 0) {
			boardVO.getAttachList().forEach(attach -> {
				attach.setBno(boardVO.getBno());
				attachMapper.insert(attach);
			});
		}
		
		return updateResult;
	}

	@Transactional
	@Override
	public boolean delete(Integer bno) {
		log.info("BoardService delete()...............");
		attachMapper.deleteAll(bno);
		return boardMapper.delete(bno) == 1;
	}

	@Override
	public List<BoardVO> getList(Criteria criteria) {
		log.info("BoardService getList()...............");
		log.info("critera : " + criteria);
		return boardMapper.getListWithPaging(criteria);
	}

	@Override
	public int getTotalCount(Criteria criteria) {
		log.info("BoardService getTotalCount()...............");
		return boardMapper.getTotalCount(criteria);
	}

	@Override
	public List<BoardAttachVO> getAttachList(Integer bno) {
		log.info("BoardService getAttachList()...............");
		log.info("bno : "+ bno);
		return attachMapper.findByBno(bno);
	}

	@Override
	public void deleteAttach(Integer bno) {
		log.info("BoardService deleteAttach()...............");
		attachMapper.deleteAll(bno);
	}

}
