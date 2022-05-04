package com.scon.project.admin.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.scon.project.admin.board.model.dto.BoardDTO;
import com.scon.project.admin.board.model.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/admin")
public class BoardController {

	
	private BoardService boardService;
	
	
	@Autowired
	public BoardController(BoardService boardService) {
		this.boardService = boardService;
		
	}
	
	@GetMapping("brdTables")
	public ModelAndView findBoardList(ModelAndView mv) {
		
		List<BoardDTO> boardList = boardService.findAllBoardList();
		
		mv.addObject("boardList", boardList);
		mv.setViewName("admin/board/brdTables");
		
		return mv;
		
	}
	
	
	
	
	
//	@GetMapping("/brdTables")
//	public String brdTables() {
//		
//		return "admin/board/brdTables";
//	}	
//	@GetMapping("/brdForm")
//	public String brdForm() {
//		
//		return "admin/board/brdForm";
//	}	
//	@GetMapping("/brdDetail")
//	public String brdDetail() {
//		
//		return "admin/board/brdDetail";
//	}	
	
}
