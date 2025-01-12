package com.mystory001.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mystory001.domain.BoardVO;
import com.mystory001.service.BoardService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller // 해당 클래스가 Controller임을 알려줌. 스프링의 빈으로 인식할 수 있게 함
@Log4j
@RequestMapping("/board/*")
@AllArgsConstructor
public class BoardController {

	private BoardService boardService;
	
	// 게시물의 목록을 전달해야하므로 Model을 파라미터로 지정하고, BoardService 객체의 getList() 결과를 담아 전달
	@GetMapping("/list")
	public void list(Model model) {
		
		log.info("BoardController list()......................");
		
		model.addAttribute("list", boardService.getList());
	}
	
	@PostMapping("/insert")
	public String insert(BoardVO boardVO, RedirectAttributes redirectAttributes) {
		log.info("BoardController insert()....................");
		log.info("boardVO : " + boardVO);
		redirectAttributes.addFlashAttribute("bno", boardVO.getBno());
		boardService.insert(boardVO);
		return "redirect:/board/list";
	}
	
	@GetMapping("/get")
	public void get(Integer bno, Model model) {
		log.info("BoardController get()....................");
		model.addAttribute("bno", boardService.get(bno));
	}
	
	@PostMapping("/update")
	public String update(BoardVO boardVO, RedirectAttributes redirectAttributes) {
		log.info("BoardController update()....................");
		log.info("수정 전 boardVO : " + boardVO);
		
		if(boardService.update(boardVO)) {
			redirectAttributes.addFlashAttribute("result", "success");
			log.info("수정 후 boardVO : " + boardVO);
		}
		return "redirect:/board/list";
	}

	@PostMapping("/delete")
	public String delete(Integer bno, RedirectAttributes redirectAttributes) {
		log.info("BoardController delete()....................");
		log.info("bno............. : " + bno);
		if(boardService.delete(bno)) {
			redirectAttributes.addFlashAttribute("result", "success");
		}
		return "redirect:/board/list";
	}
	
}
