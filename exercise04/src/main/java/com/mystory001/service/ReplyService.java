package com.mystory001.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mystory001.domain.Criteria;
import com.mystory001.domain.ReplyVO;
import com.mystory001.mapper.ReplyMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class ReplyService implements ReplyServiceInterface{

	@Setter(onMethod_ = {@Autowired})
	private ReplyMapper replyMapper;
	
	@Override
	public int insert(ReplyVO replyVO) {
		log.info("ReplyService insert()...............");
		return replyMapper.insert(replyVO);
	}

	@Override
	public ReplyVO get(int rno) {
		log.info("ReplyService get()...............");
		return replyMapper.get(rno);
	}

	@Override
	public int delete(int rno) {
		log.info("ReplyService delete()...............");
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

}
