package com.scon.project.student.taskBoard.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.scon.project.admin.taskBoard.model.dto.FileDTO;
import com.scon.project.admin.taskBoard.model.dto.TaskBoardDTO;
import com.scon.project.common.paging.Criteria;
import com.scon.project.common.paging.Pagination;
import com.scon.project.member.model.dto.UserImpl;
import com.scon.project.student.taskBoard.model.service.StudentTaskBoardService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/student")
public class StudentTaskBoardController {
	
	private StudentTaskBoardService studentTaskBoardService;
	private String uploadFilesPath;

	@Autowired
	public StudentTaskBoardController(StudentTaskBoardService studentTaskBoardService, @Value("${custom.path.upload-files}") String uploadFilesPath) {
		this.studentTaskBoardService = studentTaskBoardService;
		this.uploadFilesPath = uploadFilesPath;
	}
	
	//학생용 게시판 조회 
	@GetMapping("/taskBoard")
	public ModelAndView findAllStudentTask(@RequestParam int clsId, @RequestParam(value="keyword", required=false) String keyword, ModelAndView mv, @ModelAttribute Criteria cri) {
		
		TaskBoardDTO taskBoard = new TaskBoardDTO();
		taskBoard.setKeyword(keyword);
		taskBoard.setClsId(clsId);
		
		log.info("clsId : {} ", clsId);
		log.info("keyword : {} ", keyword);
		
		int total = studentTaskBoardService.total(taskBoard);
		Pagination page = new Pagination(cri, total);
		List<TaskBoardDTO> taskList = studentTaskBoardService.findAllStudentTask(taskBoard, cri);
		
		mv.addObject("page", page);
		mv.addObject("taskList",taskList);
		mv.setViewName("/student/taskBoard/taskBoardList");
		
		return mv;
		
	}
	
	//상세 조회
	@GetMapping("/taskDetail")
	public ModelAndView findStudentTaskDetail(@RequestParam int clsId, @RequestParam String taskId, ModelAndView mv, @AuthenticationPrincipal UserImpl user) {
		
		List<TaskBoardDTO> detailList = studentTaskBoardService.findStudentTaskDetail(taskId);
		List<FileDTO> fileList = studentTaskBoardService.findTaskFiles(taskId);
		//조회수 증가
		studentTaskBoardService.updateView(taskId);
		
		mv.addObject("detailList", detailList);
		mv.addObject("fileList",fileList);
		mv.addObject("loginUser", user);
		mv.setViewName("/student/taskBoard/taskDetail");
		
		return mv;
	}
	
	
	
	
}
