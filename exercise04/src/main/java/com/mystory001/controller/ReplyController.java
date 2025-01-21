package com.mystory001.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mystory001.domain.Criteria;
import com.mystory001.domain.ReplyVO;
import com.mystory001.service.ReplyServiceInterface;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@RestController
@RequestMapping("/reply/*")
@Log4j
@AllArgsConstructor
public class ReplyController {
	
	private ReplyServiceInterface replyService;
	
	@PostMapping(value = "/new", consumes = "application/json", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> create(@RequestBody ReplyVO replyVO){
		log.info("ReplyController create()...............");
		log.info("replyVO = " + replyVO);
		log.info("댓글 등록 개수 : "+ replyService.insert(replyVO));
		
		return replyService.insert(replyVO) == 1 ? new ResponseEntity<String>("success", HttpStatus.OK) : new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	/*
	 * create() : @PostMaing으로 동작하도록 설계, consumes와 produces를 이용해서 JSON 방식의 데이터만 처리하도록 하고, 문자열을 반환하도록 설계
	 *            파라미터 @RequestBody를 적용해서 JSON 데이터를 ReplyVO 타입으로 변환하도록 지정
	 *            내부적으로 ReplyService를 호출해서 insert()를 호출하고 댓글이 추가된 숫자를 확인해서 브라우저에서 200 또는 500을 반환하도록 함
	 * → 테스트는 REST Client를 이용해서
	 */
	
	/* 특정 게시물의 댓글 목록 확인 */
	@GetMapping(value = "/pages/{bno}/{page}", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ResponseEntity<List<ReplyVO>> getListWithPaging(@PathVariable("page") int page, @PathVariable("bno") int bno){
		log.info("ReplyController getListWithPaging()...............");
		Criteria criteria = new Criteria(page,10);
		log.info("criteria : " + criteria);
		return new ResponseEntity<>(replyService.getListWithPaging(criteria, bno), HttpStatus.OK);
	}
	/*
	 * getListWithPaging() : Criteria를 이용해서 파라미터를 수집.
	 *                       '/{bno}/{page}의 'page'값은 Criteria를 생성해서 직접 처리해야함
	 *                       게시물의 번호는 @PathVariable을 이용해서 파라미터로 처리하고 브라우저에서 http://localhost:8080/reply/pages/2184930/1 입력하면 XML 타입으로 댓글 데이터들이 나옴
	 *                       만약 JSON 타입을 원한다면 http://localhost:8080/reply/pages/2184930/1.json을 입력
	 */

	/* 댓글 조회 */
	@GetMapping(value="/{rno}", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ResponseEntity<ReplyVO> get(@PathVariable("rno") int rno){
		log.info("ReplyController get()...............");
		log.info("get : " + rno);
		return new ResponseEntity<ReplyVO>(replyService.get(rno),HttpStatus.OK);
	}
	
	/* 댓글 삭제 */
	@DeleteMapping(value = "/{rno}", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> delete(@PathVariable("rno") int rno){
		log.info("ReplyController delete()...............");
		log.info("delete : " + rno);
		return replyService.delete(rno)==1? new ResponseEntity<String>("success", HttpStatus.OK) : new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	/* 댓글 수정 */
	@RequestMapping(method = {RequestMethod.PUT, RequestMethod.PATCH}, value = "/{rno}", consumes = "application/json", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> update(@RequestBody ReplyVO replyVO, @PathVariable("rno") int rno){
		replyVO.setBno(rno);
		log.info("rno : " + rno);
		log.info("update : " + replyVO);
		return replyService.update(replyVO)==1? new ResponseEntity<String>("success",HttpStatus.OK) : new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	/*
	 * 댓글의 조회/수정/삭제는 유사한 방식으로 JSON이나 문자열을 반환하도록 설계
	 * 댓글 수정은 PUT 방식이나 PATCH 방식을 이용하도록 처리하고, 실제 수정되는 데이터는 JSON 포맷이므로 @RequestBody를 이용해서 처리. @RequestBody로 처리되는 데이터는 일반 파라미터나 @PathVariable 파라미터를 처리할 수 없기 때문에 직접 처리해 주는 부분을 주의
	 */
	
	
}
