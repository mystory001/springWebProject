package com.mystory001.mapper;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mystory001.domain.BoardVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardMapperTests {
	
//	@Autowired
//	@Inject
//	private BoardVO boardVO;
// BoardVO는 Spring 컨테이너에서 관리하는 빈이 아니므로 @Inject, @Autowired 어노테이션을 통해 의존성 주입 불가
	
	private BoardVO boardVO;
	
	@Before
	public void setUp() {
		boardVO = new BoardVO();
	}
	
	@Setter(onMethod_ = {@Autowired})
	private BoardMapper boardMapper;
	
	@Test
	public void testGetList() {
		log.info("BoardMapper testGetList()..................");
		log.info("boardMapper.getList()");
		for(int i = 0; i < boardMapper.getList().size(); i++) {
			log.info(boardMapper.getList().get(i));
		}
	}
	
	@Test
	public void testInsert() {
		log.info("BoardMapper testInsert().....................");
		boardVO.setTitle("250115");
		boardVO.setContent("250115");
		boardVO.setWriter("250115");
		boardMapper.insert(boardVO);
		log.info("입력한 값 : " + boardVO);
	}

	@Test
	public void testInsertSelectKey() {
		log.info("BoardMapper testInsertSelectKey().....................");
		boardVO.setTitle("제목");
		boardVO.setContent("insertSelectKey로 작성된 글");
		boardVO.setWriter("작성자");
		boardMapper.insertSelectKey(boardVO);
		log.info("입력한 값 : " + boardVO);
	}
	
	@Test
	public void testGet() {
		log.info("BoardMapper testGet().....................");
		log.info(boardMapper.get(1));
	}

	@Test
	public void testDelete() {
		log.info("BoardMapper testDelete().....................");
		boardMapper.delete(141);
	}
	
	@Test
	public void testUpdate() {
		log.info("BoardMapper testUpdate().....................");
		boardVO.setBno(1);
		boardVO.setTitle("update title");
		boardVO.setContent("update Content");
		boardVO.setWriter("update user");
		boardMapper.update(boardVO);
		log.info("업데이트 된 내용 : " + boardVO);
	}
	

}
