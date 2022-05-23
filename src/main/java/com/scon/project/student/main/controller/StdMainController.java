package com.scon.project.student.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.scon.project.admin.consultant.model.dto.ConsultantDTO;
import com.scon.project.admin.notice.model.dto.NoticeDTO;
import com.scon.project.admin.notice.model.service.NoticeService;
import com.scon.project.common.paging.Criteria;
import com.scon.project.member.model.dto.UserImpl;
import com.scon.project.student.consultant.model.service.ConsultantHopeService;
import com.scon.project.student.notice.model.service.StudentNoticeService;

import lombok.extern.slf4j.Slf4j;

@Configuration
@RequestMapping("/")
@Slf4j
public class StdMainController {
	
	private ConsultantHopeService consultantHopeService;
	private StudentNoticeService studentNoticeService;
	
	@Autowired
	public StdMainController(ConsultantHopeService consultantHopeService, StudentNoticeService studentNoticeService) {
		this.consultantHopeService = consultantHopeService;
		this.studentNoticeService = studentNoticeService;
	}
	
	@GetMapping("/student")
	public ModelAndView studentMain(@ModelAttribute Criteria cri, ModelAndView mv, @AuthenticationPrincipal UserImpl user) throws Exception {
		
		cri.setMemberId(user.getId());
		log.info("접속한 userId : {}", cri.getMemberId());
		
		List<ConsultantDTO> consultantList = consultantHopeService.selectAllConsultantList(cri);
		log.info("상담 신청 내역 : {}", consultantList);
		mv.addObject("consultantList", consultantList);
		
		List<NoticeDTO> noticeList = studentNoticeService.findAllNoticeList(cri);	
		mv.addObject("noticeList", noticeList);
		log.info("공지사항 : {}", noticeList);
		
		
		mv.setViewName("/student/main/stdMain");
		
		return mv;
	}

}
