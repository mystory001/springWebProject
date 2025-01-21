package com.mystory001.service;

import static org.junit.Assert.assertNotNull;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mystory001.domain.BoardVO;
import com.mystory001.domain.Criteria;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class BoardServiceTests {
	
//	@Setter(onMethod_ = {@Autowired})
//	@Autowired
	@Inject
	private BoardService boardService;
	
	private BoardVO boardVO;
	
	@Before
	public void setUp() {
		boardVO = new BoardVO();
	}
	
	@Test
	public void test() {
		log.info("BoardServiceTests test()...............");
		log.info(boardService);
		assertNotNull(boardService);
	}
	
	@Test
	public void testIntert() {
		log.info("BoardServiceTests testInsert()...............");
		boardVO.setTitle("새로 작성하는 글");
		boardVO.setContent("새로 작성하는 글 내용");
		boardVO.setWriter("새로 작성하는 작성자");
		boardService.insert(boardVO);
		log.info(boardVO);
	}
	
	@Test
	public void testUpdate() {
		log.info("BoardServiceTests testUpdate()...............");
		boardVO.setBno(2);
		boardVO.setTitle("수정 작성하는 글");
		boardVO.setContent("수정 작성하는 글 내용");
		boardVO.setWriter("수정 작성하는 작성자");
		boardService.update(boardVO);
		log.info(boardVO);
	}
	
	@Test
	public void testDelete() {
		log.info("BoardServiceTests testDelete()...............");
		boardService.delete(3);
	}
	
	@Test
	public void testGet() {
		log.info("BoardServiceTests testGet()...............");
//		log.info(boardService.get(4));
		boardService.getList(new Criteria(2,10)).forEach(boardVO -> log.info(boardVO));
	}

}
