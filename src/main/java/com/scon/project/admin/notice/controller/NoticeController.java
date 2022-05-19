package com.scon.project.admin.notice.controller;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.scon.project.admin.notice.model.dto.NoticeCmtDTO;
import com.scon.project.admin.notice.model.dto.NoticeDTO;
import com.scon.project.admin.notice.model.service.NoticeService;
import com.scon.project.common.paging.Criteria;
import com.scon.project.common.paging.Pagination;
import com.scon.project.member.model.dto.UserImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/admin")
public class NoticeController {

	private NoticeService noticeService;

	@Autowired
	public NoticeController(NoticeService noticeService) {
		this.noticeService = noticeService;

	}
	
//	등록된 공지사항 목록 조회+페이징
	@GetMapping("notiTables")
	public ModelAndView findNoticeList(ModelAndView mv, Criteria cri) throws Exception {

		List<NoticeDTO> noticeList = noticeService.findAllNoticeList(cri);		

		mv.addObject("noticeList", noticeList);
		mv.setViewName("admin/notice/notiTables");		

		int total = noticeService.total(cri);
		Pagination page = new Pagination(cri, total);
		mv.addObject("page", page);

		return mv;
	}
	
//	공지 작성 페이지 이동
	@GetMapping("notiForm")
	public String registPage() {
		return "admin/notice/notiForm";
	}

	
/* 게시글 */
	
//	공지 글 등록
	@PostMapping("notiForm")
	public String registNotice(@ModelAttribute NoticeDTO notice, RedirectAttributes rttr) throws Exception {

		noticeService.registNotice(notice);
		
		return "redirect:notiTables";
	}

//	게시글 상세 조회
	@GetMapping("notiDetail") 
	public String selectNotice(@RequestParam int no, Model model) throws Exception {
		
		model.addAttribute("noticeDetail", noticeService.selectNoticeDetail(no));
		// 댓글 조회
		List<NoticeCmtDTO> cmtList = noticeService.readCmt(no);
		model.addAttribute("cmtList", cmtList);
		log.info("댓글목록 : {}", cmtList);
		
		return "admin/notice/notiDetail";
    }
		
//	게시글 수정(조회)
	@GetMapping("notiModify") 
	public String modifyNoticeDetail(@RequestParam int no, Model model) throws Exception {
		
		model.addAttribute("modifyNotice", noticeService.selectNoticeDetail(no));

		return "admin/notice/notiModify";
    }

//	게시글 수정(등록)
	@PostMapping("notiModify")
	public String modifyNotice(@ModelAttribute NoticeDTO notice, RedirectAttributes rttr) throws Exception {

		noticeService.modifyNotice(notice);
		log.info("notice{}", notice);
		
		return "redirect:notiTables";
	}
	
// 	게시글 삭제
	@PostMapping("notiDelete")
	public String deleteNotice(@RequestParam int no, RedirectAttributes rttr) throws Exception {
		
		noticeService.deleteNotice(no);		
		
		return "redirect:notiTables";		
	}

	
	
	
	
/* 댓글 */	
	
//	댓글 등록
	@PostMapping("cmtRegi")
	public String registCmt(@ModelAttribute NoticeCmtDTO notiCmt, 
			@AuthenticationPrincipal UserImpl user) throws Exception {
		
		log.info("로그인user : {}", user);
		notiCmt.setMemberId(user.getId());
				
		noticeService.registCmt(notiCmt);		
		
		return "redirect:notiDetail?no="+notiCmt.getNo();
	}
	
//	댓글 삭제
	@PostMapping("cmtDelete")
	public String deleteCmt(@RequestParam("cNo") int cNo, 
			@ModelAttribute NoticeCmtDTO notiCmt) throws Exception {
		
		log.info("삭제될 댓글 번호 : {}", cNo);
		noticeService.deleteCmt(cNo);		
		
		
		return "redirect:notiDetail?no="+notiCmt.getNo();
	}

//	댓글 수정(조회)
	@GetMapping("cmtModify") 
	public String modifyCmt(@RequestParam("cNo") int cNo, Model model, @ModelAttribute NoticeCmtDTO notiCmt) throws Exception {
		
		model.addAttribute("modifyCmt", noticeService.readCmt(cNo));

		return "redirect:notiDetail?no="+notiCmt.getNo();
    }

//	댓글 수정(등록)
	@PostMapping("cmtModify")
	public String modifyCmt(@ModelAttribute NoticeCmtDTO notiCmt) throws Exception {

		noticeService.modifyCmt(notiCmt);
		log.info("notiCmt{}", notiCmt);
		
		return "redirect:notiDetail?no="+notiCmt.getNo();
	}

	
}
