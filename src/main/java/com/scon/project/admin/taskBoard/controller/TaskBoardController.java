package com.scon.project.admin.taskBoard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.scon.project.admin.taskBoard.model.dto.TaskBoardDTO;
import com.scon.project.admin.taskBoard.model.service.TaskBoardService;

@Controller
@RequestMapping("/admin")
public class TaskBoardController {

	private TaskBoardService taskBoardService;
	
	//의존성 주입
	@Autowired
	public TaskBoardController(TaskBoardService taskBoardService) {
		this.taskBoardService = taskBoardService;
	}
	
	@GetMapping("/insertTask")
	public String insertTask() {
		return "/admin/taskBoard/insertTask";
	}
	
/*	@GetMapping("/taskBoardList")
	public String taskBoardList() {
		return "admin/taskBoard/taskBoardList";
	} */
	
	// 과제 게시판 전체 조회
//	@GetMapping("/taskBoardList")
//	public ModelAndView findAllTask(ModelAndView mv) {
//		
//		List<TaskBoardDTO> taskList = taskBoardService.findAllTask();
//		
//		mv.addObject("taskList", taskList);
//		mv.setViewName("admin/taskBoard/taskBoardList");
//		
//		return mv;
//	}
//	
	
	@GetMapping("/taskDetail")
	public String taskDetail() {
		return "admin/taskBoard/taskDetail";
	}
	
	
	/*
	 * 	@PostMapping("multi-file")
	public String multiFileUpload(@RequestParam List<MultipartFile> multiFiles, HttpServletRequest request, Model model) {
		
		String multiFileDescription = request.getParameter("multiFileDescription");
		System.out.println("multiFiles : " + multiFiles);
		System.out.println("multiFileDescription : " + multiFileDescription);
		
		// 파일을 저장할 경로 설정 
		String root = request.getSession().getServletContext().getRealPath("resources");
		
		System.out.println("root : " + root);
		
		String filePath = root + "\\uploadFiles";
		
		File mkdir = new File(filePath);
		if(!mkdir.exists()) {
			mkdir.mkdirs();
		}
		
		List<Map<String, String>> files = new ArrayList<>();
		
		for(int i = 0; i < multiFiles.size(); i++) {
			//파일명 변경 처리
			String originFileName = multiFiles.get(i).getOriginalFilename();
			String ext = originFileName.substring(originFileName.lastIndexOf("."));
			String savedName = UUID.randomUUID().toString().replace("-", "") + ext;
			
			// 파일에 관한 정보 추출 후 보관 
			Map<String, String> file = new HashMap<>();
			file.put("originFileName", originFileName);
			file.put("savedName", savedName);
			file.put("filePath", filePath);
			
			files.add(file);
		}
		
		try {
		
			// 파일을 저장한다. 
			for(int i = 0; i < multiFiles.size(); i++) {
				multiFiles.get(i).transferTo(new File(filePath + "\\" + files.get(i).get("savedName")));
			}
			
			model.addAttribute("message", "파일 업로드 성공!");
			
		} catch (IllegalStateException | IOException e) {
			
			// 업로드 실패 시 이전에 저장 된 파일도 삭제한다. 
			for(int i = 0; i < multiFiles.size(); i++) {
				new File(filePath + "\\" + files.get(i).get("savedName")).delete();
			}
			
			model.addAttribute("message", "파일 업로드 실패!");
		}
		
		return "result";
	} */
	
	
	
	
}


