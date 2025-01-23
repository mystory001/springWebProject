package com.mystory001.mapper;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mystory001.domain.Criteria;
import com.mystory001.domain.ReplyVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ReplyMapperTests {

	
	private Integer[] bnoArr = {2184930, 2184928, 2184927, 2184926, 2184925, 2184924, 2184923, 2184922}; // 테스트 전 해당 번호의 게시물의 존재여부 확인
	
	@Setter(onMethod_ = {@Autowired})
	private ReplyMapper replyMapper;
	
	@Test
	public void test() {
		log.info(replyMapper);
	}
	
	@Test
	public void testInsert() {
		log.info("ReplyMapperTests testInsert()...............");
		IntStream.rangeClosed(1, 10).forEach(i ->{
												ReplyVO replyVO = new ReplyVO();
												replyVO.setBno(bnoArr[i%5]);
												replyVO.setReply("댓글 테스트" + i);
												replyVO.setReplyer("댓글 테스터" + i);
												replyMapper.insert(replyVO);
												});
	}
	
	@Test
	public void testGet() {
		log.info("ReplyMapperTests testGet()...............");
		log.info(replyMapper.get(5));
	}
	
	@Test
	public void testDelete() {
		log.info("ReplyMapperTests testDelete()...............");
		replyMapper.delete(4);
		log.info(replyMapper.get(4));
	}
	
	@Test
	public void testUpdate() {
		log.info("ReplyMapperTests testUpdate()...............");
		ReplyVO replyVO = replyMapper.get(10);
		
		replyVO.setReply("수정된 댓글");
		
		replyMapper.update(replyVO);
		log.info("수정한 댓글 수...................... : " + replyMapper.update(replyVO));
		log.info(replyMapper.get(10));
	}
	
	@Test
	public void testGetListWithPaging() {
		Criteria criteria = new Criteria();
		// 2184928
		List<ReplyVO> list = replyMapper.getListWithPaging(criteria, 2184928);
		for(int i = 0; i < list.size(); i++) {
			log.info(list);
		}
	}
	
	@Test
	public void testGetListWithPaging2() {
		Criteria criteria = new Criteria(2,10);
		// 2184930
		List<ReplyVO> list = replyMapper.getListWithPaging(criteria, 2184930);
		for(int i = 0; i < list.size(); i++) {
			log.info(list);
		}
	}
	
	
	
	
}
