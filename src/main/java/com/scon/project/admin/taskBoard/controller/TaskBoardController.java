package com.scon.project.admin.taskBoard.controller;



import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.scon.project.admin.taskBoard.model.dto.FileDTO;
import com.scon.project.admin.taskBoard.model.dto.TaskBoardDTO;
import com.scon.project.admin.taskBoard.model.service.TaskBoardService;
import com.scon.project.member.model.dto.UserImpl;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/admin")
@Slf4j
public class TaskBoardController {

	private TaskBoardService taskBoardService;
	private ObjectMapper objMapper;
	
	//의존성 주입
	@Autowired
	public TaskBoardController(TaskBoardService taskBoardService) {
		this.taskBoardService = taskBoardService;
	}
	
	@GetMapping("/insertTask")
	public String insertTask() {
		return "/admin/taskBoard/insertTask";
	}
	
//	@GetMapping("/taskBoardList")
//	public String taskBoardList() {
//		return "admin/taskBoard/taskBoardList";
//	} 
	
	// 과제 게시판 전체 조회 (O)
	@GetMapping("/taskBoardList")
	public ModelAndView findAllTask(@RequestParam int clsId, ModelAndView mv) {
		
		List<TaskBoardDTO> taskList = taskBoardService.findAllTask(clsId);
		
		mv.addObject("taskList", taskList);
		mv.setViewName("admin/taskBoard/taskBoardList");
		
		return mv;
	}
	
	
	@GetMapping("/taskDetail")
	public String taskDetail() {
		return "admin/taskBoard/taskDetail";
	}
	
	
	/* 과제 게시판 글 입력 */
//	@PostMapping("/insertTask")
//	public String multiFileUpload(@RequestParam(value="taskFile") List<MultipartFile> multiFiles, @ModelAttribute TaskBoardDTO task, 
//			HttpServletRequest request, @AuthenticationPrincipal UserImpl user) throws Exception {
//		
//		String page = "";
//		
//		log.info("로그인 유저 : {} ", user);
//		task.setMemberId(user.getId());
//		
//		log.info("task : {} ", task);
//		
//		// 파일을 저장할 경로 설정  (경로를 프로젝트 내부로)
//		String root = request.getSession().getServletContext().getRealPath("resources");
//		
//		String filePath = root + "\\uploadFiles";
//		
//		log.info("filePath : {} ", filePath);
//		
//		File mkdir = new File(filePath);
//		if(!mkdir.exists()) {
//			mkdir.mkdirs();
//		}
//		
//		List<Map<String, String>> files = new ArrayList<>();
//		List<FileDTO> infoList = new ArrayList<FileDTO>();
//		
//		
//		for(int i = 0; i < multiFiles.size(); i++) {
//			FileDTO fileInfo = new FileDTO();
//			
//			//파일명 변경 처리
//			String originFileName = multiFiles.get(i).getOriginalFilename();
//			String ext = originFileName.substring(originFileName.lastIndexOf("."));
//			String savedName = UUID.randomUUID().toString().replace("-", "") + ext;
//			
//			// 파일에 관한 정보 추출 후 보관 
//			Map<String, String> file = new HashMap<>();
//			file.put("originFileName", originFileName);
//			file.put("savedName", savedName);
//			file.put("filePath", filePath);   */
//	
//			fileInfo.setFileOrginName(originFileName);
//			fileInfo.setFileSaveName(savedName);
//			
//			/* List 타입에 담기 */
//			infoList.add(fileInfo);
//			
//			/* TaskDTO에 담기 */
//			task.setFileList(infoList);
//		}
//
//	//	log.info("files : {}", files);
//		log.info("infoList : {} ", infoList);
//
//		//task 객체 안에 파일 정보까지 담아서 1.테스크 테이블 insert 2.파일 테이블 insert 3.테스트 파일 테이블 insert 3개가 잘 됐을 때 로직 성공
//		// 3개 DAO에 선언 된 메소드를 호출 
//		int result = taskBoardService.insertTask(task);
//
//		if(result > 0) {
//			page = "redirect:admin/taskBoard/taskBoardList";
//		} else {
//			throw new Exception("게시글 등록 실패!");
//		}
//
//		try {
//		
//			// 파일을 저장한다. 
//			for(int i = 0; i < multiFiles.size(); i++) {
//				multiFiles.get(i).transferTo(new File(filePath + "\\" + files.get(i).get("savedName")));
//			}
//			
//			
//		} catch (IllegalStateException | IOException e) {
//			
//			// 업로드 실패 시 이전에 저장 된 파일도 삭제한다. 
//			for(int i = 0; i < multiFiles.size(); i++) {
//				new File(filePath + "\\" + files.get(i).get("savedName")).delete();
//			}
//			
//		}
//		
//		return page;
//		
//	} 
	 
	
	
	
}


