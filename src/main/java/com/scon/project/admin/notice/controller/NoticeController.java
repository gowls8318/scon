package com.scon.project.admin.notice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.scon.project.admin.notice.model.dto.NoticeDTO;
import com.scon.project.admin.notice.model.service.NoticeService;
import com.scon.project.admin.notice.paging.Criteria;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/admin")
public class NoticeController {

	private NoticeService noticeService;
	private MessageSource messageSource;

	@Autowired
	public NoticeController(NoticeService noticeService) {
		this.noticeService = noticeService;

	}
//	등록된 공지사항 목록 조회 + 페이징 + 페이지네이션
	@GetMapping("notiTables")
	public ModelAndView findNoticeList(ModelAndView mv, Criteria cri) throws Exception {

		List<NoticeDTO> noticeList = noticeService.findAllNoticeList(cri);

		mv.addObject("noticeList", noticeList);
		mv.setViewName("admin/notice/notiTables");
		
//		PageMaker pageMaker = new PageMaker();
//		pageMaker.setCri(cri);
//		pageMaker.setTotalCount(noticeService.allNoticeCount());
//		mv.addObject("paceMaker", pageMaker);
		
		
		return mv;

	}
	
	
	
	
	
	
	
	
	
	
	
	
//	공지 작성 페이지 이동
	@GetMapping("notiForm")
	public String registPage() {
		return "admin/notice/notiForm";
	}

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

		return "admin/notice/notiDetail";

    }
	
//	게시글 수정
	@PutMapping("notiDetail")
	public String selectNoticeAction(NoticeDTO noti) throws Exception {

		noticeService.registNotice(noti);
		
		return "redirect:notiDetail";
	}
	
	
	
	
	
	
	
//	게시글 상세 조회
//	@GetMapping("notiDetail")
//	public ModelAndView openNoticeDetail(@RequestParam int idx) throws Exception {
//		
//		ModelAndView mv = new ModelAndView("/admin/notiDetail");
//				
//		NoticeDTO notice = noticeService.sellectNoticeDetail(idx);
//		
//		mv.addObject("notice", notice);
//
//		return mv;
//
//    }

	
	
	
	
	
	
	
	
	
	
	
	
//	
//	@GetMapping("/notiForm")
//	public String noticeForm() {
//		
//		return "admin/notice/notiForm";
//	}
//	
//	@GetMapping("/notiDetail")
//	public String noticeDetail() {
//		
//		return "admin/notice/notiDetail";
//	}	
//	
}
