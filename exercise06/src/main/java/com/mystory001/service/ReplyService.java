package com.mystory001.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mystory001.domain.Criteria;
import com.mystory001.domain.ReplyPageDTO;
import com.mystory001.domain.ReplyVO;
import com.mystory001.mapper.BoardMapper;
import com.mystory001.mapper.ReplyMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class ReplyService implements ReplyServiceInterface{

	@Setter(onMethod_ = {@Autowired})
	private ReplyMapper replyMapper;
	
	@Setter(onMethod_ = {@Autowired})
	private BoardMapper boardMapper;
	
	@Transactional
	@Override
	public int insert(ReplyVO replyVO) {
		log.info("ReplyService insert()...............");
		boardMapper.updateReplyCnt(replyVO.getBno(), 1);
		return replyMapper.insert(replyVO);
	}

	@Override
	public ReplyVO get(int rno) {
		log.info("ReplyService get()...............");
		return replyMapper.get(rno);
	}

	@Transactional
	@Override
	public int delete(int rno) {
		log.info("ReplyService delete()...............");
		ReplyVO replyVO = new ReplyVO();
		boardMapper.updateReplyCnt(replyVO.getBno(), -1);
		return replyMapper.delete(rno);
	}

	@Override
	public int update(ReplyVO replyVO) {
		log.info("ReplyService update()...............");
		return replyMapper.update(replyVO);
	}

	@Override
	public List<ReplyVO> getListWithPaging(Criteria criteria, Integer bno) {
		log.info("ReplyService getListWithPaging()...............");
		return replyMapper.getListWithPaging(criteria, bno);
	}

	@Override
	public ReplyPageDTO getListPage(Criteria criteria, Integer bno) {
		return new ReplyPageDTO(replyMapper.getCountByBno(bno), replyMapper.getListWithPaging(criteria, bno));
	}

}
