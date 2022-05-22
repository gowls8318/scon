package com.scon.project.student.notice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.scon.project.admin.notice.model.dto.NoticeCmtDTO;
import com.scon.project.admin.notice.model.dto.NoticeDTO;
import com.scon.project.common.paging.Criteria;
import com.scon.project.common.paging.Pagination;
import com.scon.project.member.model.dto.UserImpl;
import com.scon.project.student.notice.model.service.StudentNoticeService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/student")
public class StudentNoticeController {

	private StudentNoticeService studentNoticeService;

	@Autowired
	public StudentNoticeController(StudentNoticeService studentNoticeService) {
		this.studentNoticeService = studentNoticeService;

	}

//	등록된 공지사항 목록 조회+페이징
	@GetMapping("notiTables")
	public ModelAndView findNoticeList(ModelAndView mv, Criteria cri) throws Exception {

		List<NoticeDTO> noticeList = studentNoticeService.findAllNoticeList(cri);

		mv.addObject("noticeList", noticeList);
		mv.setViewName("student/notice/stdNotiTables");

		int total = studentNoticeService.total(cri);
		Pagination page = new Pagination(cri, total);
		mv.addObject("page", page);

		return mv;
	}

	  
	  
	 // 게시글 상세 조회	  
	 @GetMapping("notiDetail") 
	 public String selectNotice(@RequestParam int no, Model model) throws Exception {
	  
		  model.addAttribute("noticeDetail", studentNoticeService.selectNoticeDetail(no));
		  //댓글 조회 
		  List<NoticeCmtDTO> cmtList = studentNoticeService.readCmt(no);
		  model.addAttribute("cmtList", cmtList); log.info("댓글목록 : {}", cmtList);
	  
		  return "student/notice/stdNotiDetail"; 
	 }
 
	
	/* 댓글 */ 
	  // 댓글 등록	  
	  @PostMapping("cmtRegi") 
	  public String registCmt(@ModelAttribute NoticeCmtDTO notiCmt, 
			  @AuthenticationPrincipal UserImpl user) throws Exception {
	  
		  log.info("로그인user : {}", user); notiCmt.setMemberId(user.getId());
		  
		  studentNoticeService.registCmt(notiCmt);
		  
		  return "redirect:notiDetail?no="+notiCmt.getNo(); 
	  }
	  
	  
	 // 댓글 수정(조회)
	 
	  @GetMapping("cmtModify") 
	  public String modifyCmt(@RequestParam("cNo") int cNo, Model model, 
			  @ModelAttribute NoticeCmtDTO notiCmt) throws Exception {
	  
	  log.info("수정할 댓글 번호 : {}", cNo); 
	  model.addAttribute("modifyCmt", studentNoticeService.readCmt(cNo));
	  
	  return "redirect:notiDetail?no="+notiCmt.getNo(); }
	  
	  
	  // 댓글 수정(등록)
	  
	 @PostMapping("cmtModify") 
	 public String modifyCmt(@ModelAttribute NoticeCmtDTO notiCmt) throws Exception {
		  
		 studentNoticeService.modifyCmt(notiCmt); 
		 log.info("수정할 댓글 번호{}", notiCmt);
		  
		 return "redirect:notiDetail?no="+notiCmt.getNo(); 
	 }
	  
	 
	  
	  // 댓글 삭제
	  @PostMapping("cmtDelete") 
	  public String deleteCmt(@RequestParam("cNo") int cNo, 
			  @ModelAttribute NoticeCmtDTO notiCmt) throws Exception {
	  
		  log.info("삭제될 댓글 번호 : {}", cNo); studentNoticeService.deleteCmt(cNo);
		  
		  return "redirect:notiDetail?no="+notiCmt.getNo(); 
	  }
	 

}
