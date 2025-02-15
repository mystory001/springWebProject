package com.mystory001.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mystory001.domain.BoardAttachVO;
import com.mystory001.domain.BoardVO;
import com.mystory001.domain.Criteria;
import com.mystory001.domain.PageDTO;
import com.mystory001.service.BoardSerivceInterface;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller // 해당 클래스가 Controller임을 알려줌. 스프링의 빈으로 인식할 수 있게 함
@Log4j
@RequestMapping("/board/*")
@AllArgsConstructor
public class BoardController {

	private BoardSerivceInterface boardService;
	
	// 게시물의 목록을 전달해야하므로 Model을 파라미터로 지정하고, BoardService 객체의 getList() 결과를 담아 전달
	@GetMapping("/list")
	public void list(Model model, Criteria criteria) {
		log.info("BoardController list()......................");
		model.addAttribute("list", boardService.getList(criteria));
		model.addAttribute("page", new PageDTO(criteria,boardService.getTotalCount(criteria)));
	}
	
	@GetMapping("/insert")
	public void insert() {
		log.info("BoardController insert()...............");
	}
	
	@PostMapping("/insert")
	public String insert(BoardVO boardVO, RedirectAttributes redirectAttributes) {
		log.info("BoardController insert()....................");
//		boardService.insert(boardVO);
//		redirectAttributes.addFlashAttribute("result", boardVO.getBno()); 
		
		log.info("boardVO : " + boardVO);
		if(boardVO.getAttachList() != null) {
			boardVO.getAttachList().forEach(attach -> log.info(attach));
		}
		
		log.info("==========================");
		
		boardService.insert(boardVO);
		redirectAttributes.addFlashAttribute("result", boardVO.getBno());
		
		return "redirect:/board/list";
	}
	
//	@GetMapping("/get")
//	public void get() {
//		log.info("BoardController get()...............");
//		model.addAttribute("boardVO", boardService.get(bno));
//	}

	//	@GetMapping("/update")
//	public void update() {
//		log.info("BoardController update()...............");
//		model.addAttribute("boardVO", boardService.get(bno));
//	}

	@GetMapping({"/get", "/update"})
	public void get(@RequestParam("bno") Integer bno, Model model, @ModelAttribute("criteria") Criteria criteria) {
		log.info("BoardController get() || BoardController update()....................");
		model.addAttribute("boardVO", boardService.get(bno));
		log.info(bno);
	}
	
	@PostMapping("/update")
	public String update(BoardVO boardVO, RedirectAttributes redirectAttributes, @ModelAttribute("criteria") Criteria criteria) {
		log.info("BoardController update()....................");
		log.info("수정 전 boardVO : " + boardVO);
		
		if(boardService.update(boardVO)) {
			redirectAttributes.addFlashAttribute("result", "success");
			log.info("수정 후 boardVO : " + boardVO);
		}
		
		redirectAttributes.addAttribute("pageNum", criteria.getPageNum());
		redirectAttributes.addAttribute("amount", criteria.getAmount());
		redirectAttributes.addAttribute("type", criteria.getType());
		redirectAttributes.addAttribute("keyword", criteria.getKeyword());
		
		return "redirect:/board/list";
//		return "redirect:/board/list" + criteria.getListLink();
	}

	@PostMapping("/delete")
	public String delete(@RequestParam("bno") Integer bno, RedirectAttributes redirectAttributes, @ModelAttribute("criteria") Criteria criteria) {
		log.info("BoardController delete()....................");
		log.info("bno............. : " + bno);
		
		List<BoardAttachVO> attachList = boardService.getAttachList(bno);
		
		if(boardService.delete(bno)) {
			deleteFiles(attachList);
			redirectAttributes.addFlashAttribute("result", "success");
		}
		
//		redirectAttributes.addAttribute("pageNum", criteria.getPageNum());
//		redirectAttributes.addAttribute("amount", criteria.getAmount());
//		redirectAttributes.addAttribute("type", criteria.getType());
//		redirectAttributes.addAttribute("keyword", criteria.getKeyword());
		
//		return "redirect:/board/list?";
		return "redirect:/board/list?" + criteria.getListLink();
	}
	
	@GetMapping(value="/getAttachList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<List<BoardAttachVO>> getAttachList(Integer bno){
		log.info("BoardController getAttachList()....................");
		
		log.info("getAttachList : " + bno);
		
		return new ResponseEntity<List<BoardAttachVO>>(boardService.getAttachList(bno),HttpStatus.OK);
	}
	
	private void deleteFiles(List<BoardAttachVO> attachList) {
		if(attachList == null || attachList.size() == 0) {
			return;
		}
		log.info("BoardController deleteFiles()....................");
		log.info("attachList : " + attachList);
		
		attachList.forEach(attach ->{
			try {
				Path file = Paths.get("C:\\upload\\" + attach.getUploadPath() + "\\" + attach.getUuid() + "_" + attach.getFileName());
				
				Files.deleteIfExists(file);
				
				if(Files.probeContentType(file).startsWith("image")) {
					Path thumbNail = Paths.get("C:\\upload\\" + attach.getUploadPath() + "\\s_" + attach.getUuid() + "_" + attach.getFileName());
					
					Files.delete(thumbNail);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
	
}
