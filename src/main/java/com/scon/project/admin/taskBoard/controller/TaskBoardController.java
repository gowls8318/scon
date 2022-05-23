package com.scon.project.admin.taskBoard.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tika.Tika;
import org.mybatis.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.scon.project.admin.taskBoard.model.dto.FileDTO;
import com.scon.project.admin.taskBoard.model.dto.TaskBoardDTO;
import com.scon.project.admin.taskBoard.model.service.TaskBoardService;
import com.scon.project.common.paging.Criteria;
import com.scon.project.common.paging.Pagination;
import com.scon.project.member.model.dto.UserImpl;

import ch.qos.logback.classic.Logger;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/admin")
@Slf4j
public class TaskBoardController {

	private TaskBoardService taskBoardService;
	private ObjectMapper objMapper;
	private MessageSource messageSource;
	private String uploadFilesPath;
	
	
	//의존성 주입
	@Autowired
	public TaskBoardController(TaskBoardService taskBoardService, @Value("${custom.path.upload-files}") String uploadFilesPath) {
		this.taskBoardService = taskBoardService;
		this.uploadFilesPath = uploadFilesPath;
	}
	
	@Autowired
	ResourceLoader resourceLoader;

	// 과제 게시판 전체 조회 (O)
	@GetMapping("/taskBoardList")
	public ModelAndView findAllTask(@RequestParam int clsId, @RequestParam(value="keyword", required=false) String keyword, ModelAndView mv, @ModelAttribute Criteria cri) {
		
		TaskBoardDTO taskBoard = new TaskBoardDTO();	
		taskBoard.setKeyword(keyword);
		taskBoard.setClsId(clsId);		
	
	
		log.info("clsId : {} ", clsId);
		log.info("keyword : {} ", keyword);
			
		
		int total = taskBoardService.total(taskBoard);
		Pagination page = new Pagination(cri, total);
		List<TaskBoardDTO> taskList = taskBoardService.findAllTask(taskBoard,cri);
		mv.addObject("page", page);
		log.info("pagunation : {} ", page);
		log.info("taskList : {} ", taskList);
		
		mv.addObject("taskList", taskList);
		mv.setViewName("admin/taskBoard/taskBoardList");
		
		return mv;
	}
	
	//게시판 게시글 상세 조회 (O)
	@GetMapping("/taskDetail")
	public ModelAndView findDetail(@RequestParam String taskId, @RequestParam int clsId, ModelAndView mv) {
		
		TaskBoardDTO taskDetail = taskBoardService.findDetail(taskId);
		List<FileDTO> fileList = taskBoardService.findFiles(taskId);
		//조회수 증가
		taskBoardService.updateView(taskId);
		
		log.info("taskDetail : {} ",  taskDetail);
		log.info("fileList : {} " , fileList);
		
		mv.addObject("taskDetail", taskDetail);
		mv.addObject("fileList", fileList);
		mv.setViewName("admin/taskBoard/taskDetail");
		
		return mv;
	} 

	
	@GetMapping("/insertTask")
	public String insertTask() {
		return "/admin/taskBoard/insertTask";
	}
	
	// 과제 게시판 글 입력 (O)
	@PostMapping("/insertTask")
	public String multiFileUpload(@RequestParam(value="taskFile") List<MultipartFile> multiFiles, @RequestParam int clsId, @ModelAttribute TaskBoardDTO task,
			@AuthenticationPrincipal UserImpl user) throws Exception {
		
		String page = "";
		
		log.info("로그인 유저 : {} ", user);
		task.setMemberId(user.getId());
		
		log.info("task : {} ", task);
		log.info("입력 clsId : {}", clsId);
		
	
		
		File mkdir = new File(uploadFilesPath + "\\taskFiles");
		if(!mkdir.exists()) {
			mkdir.mkdirs();
		}
		
		List<FileDTO> fileList = new ArrayList<>();
		
		for(int i = 0; i < multiFiles.size(); i++) {
			FileDTO fileInfo = new FileDTO();
			
			//파일명 변경 처리
			String originFileName = multiFiles.get(i).getOriginalFilename();
			String ext = originFileName.substring(originFileName.lastIndexOf("."));
			String savedName = UUID.randomUUID().toString().replace("-", "") + ext;
			
			
			// 파일 정보 FileDTO의 setter에 담기
			fileInfo.setFileOrginName(originFileName);
			fileInfo.setFileSaveName(savedName);
			fileInfo.setFilePath("/uploadFiles/taskFiles/");
			fileInfo.setFileType(ext);

			/* List 타입에 담기 */
			fileList.add(fileInfo);
			
		}
		/* TaskDTO에 담기 */
		task.setFileList(fileList);
		task.setClsId(clsId);
		
		log.info("fileList : {} ", fileList);
		
		
		int result = taskBoardService.insertTask(task);

		if(result > 0) {
			 page = "redirect:/admin/taskBoardList?clsId=" + clsId;
		} else {
			throw new Exception("게시글 등록 실패!");
		}

		try {
		
			// 파일을 저장한다. 
			for(int i = 0; i < fileList.size(); i++) {
				multiFiles.get(i).transferTo(new File(uploadFilesPath + "\\taskFiles\\" + fileList.get(i).getFileSaveName()));
			}
			
			
		} catch (IllegalStateException | IOException e) {
			
			// 업로드 실패 시 이전에 저장 된 파일도 삭제한다. 
			for(int i = 0; i < fileList.size(); i++) {
				new File(uploadFilesPath + "\\taskFiles\\" + fileList.get(i).getFileSaveName()).delete();
			}
			
		}
		
		return page;
		
	} 

	
	//게시글 삭제 (O)
	@GetMapping("/deleteBoard")
	public String deleteBoard(@RequestParam int clsId, @RequestParam String taskId, RedirectAttributes redirectAttr) throws Exception {
		
		
		log.info("삭제 게시글 아이디 : {} ", taskId);
		log.info("클래스 아이디 : {} ", clsId);
		taskBoardService.deleteBoard(taskId);
		
		redirectAttr.addAttribute("clsId", clsId);
		return "redirect:/admin/taskBoardList";	
	}
	
		//게시글 수정 전 조회
		@GetMapping("/modifyTask")
		public ModelAndView findModifyTask(@RequestParam String taskId, @RequestParam int clsId, ModelAndView mv, @AuthenticationPrincipal UserImpl user) {
			
			List<TaskBoardDTO> detailList = taskBoardService.findModifyTask(taskId);
			List<FileDTO> fileList = taskBoardService.findModifyfiles(taskId);
			
			
			log.info("수정 전 taskDetail : {} ",  detailList);
			log.info("수정 전 fileList : {} " , fileList);
			log.info("로그인한 유저 : {} ", user);
			
			mv.addObject("detailList", detailList);
			mv.addObject("fileList", fileList);
			mv.addObject("loginUser", user);
			mv.setViewName("/admin/taskBoard/modifyTask");
			
			return mv;
		}

		//게시글 수정
		@PostMapping("/modifyTask")
		public String modifyTask(@RequestParam(value="taskFile", required=false) List<MultipartFile> multiFiles, @ModelAttribute TaskBoardDTO task, RedirectAttributes attr, HttpServletRequest request ) throws Exception {
		
			String page = "";
			List<FileDTO> fileList = new ArrayList<>();
			int clsId = Integer.parseInt(request.getParameter("clsId"));
			int fileId = Integer.parseInt(request.getParameter("fileId"));
			
			log.info("수정 요청 clsId : {} ", clsId);
			log.info("수정 요청 multiFiles : {}", multiFiles);
			log.info("수정 요청 fileId : {}", fileId);
			
			
			File mkdir = new File(uploadFilesPath + "\\taskFiles");
			FileDTO fileInfo = new FileDTO();
			
			//새로운 업로드 파일 존재 여부 확인하기
			if(!(multiFiles == null)) {   
				for(int i = 0; i < multiFiles.size(); i++) {
					
					
					//파일명 변경 처리
					String originFileName = multiFiles.get(i).getOriginalFilename();
					String ext = originFileName.substring(originFileName.lastIndexOf("."));
					String savedName = UUID.randomUUID().toString().replace("-", "") + ext;
					
					
					// 파일 정보 FileDTO의 setter에 담기
					fileInfo.setFileOrginName(originFileName);
					fileInfo.setFileSaveName(savedName);
					fileInfo.setFilePath("/uploadFiles/taskFiles/");
					fileInfo.setFileType(ext);
					fileInfo.setFileId(fileId);

					/* List 타입에 담기 */
					fileList.add(fileInfo);
					
				}
				/* TaskDTO에 담기 */
				task.setFileList(fileList);
		

				log.info("fileList : {} ", fileList);

				
				int result1 = taskBoardService.modifyTaskAndFiles(task);

				if(result1 > 0) {
				//	page = "redirect:/admin/taskBoardList?clsId=1";
				} else {
					throw new Exception("게시글 등록 실패!");
				}

				try {
					
					// 파일을 저장한다. 
					for(int i = 0; i < fileList.size(); i++) {
						multiFiles.get(i).transferTo(new File(uploadFilesPath + "\\taskFiles\\" + fileList.get(i).getFileSaveName()));
					}
					
					
				} catch (IllegalStateException | IOException e) {
					
					// 업로드 실패 시 이전에 저장 된 파일도 삭제한다. 
					for(int i = 0; i < fileList.size(); i++) {
						new File(uploadFilesPath + "\\taskFiles\\" + fileList.get(i).getFileSaveName()).delete();
					}
					
				} 	
			} else if(multiFiles == null){
				int result2 = taskBoardService.modifyOnlyContent(task);
			//	page = "redirect:/admin/taskBoardList?clsId=1";
			}
		
			attr.addAttribute("clsId", clsId);
			return "redirect:/admin/taskBoardList";	
		}

		/* 예외 처리 */
		@ExceptionHandler(value = Exception.class)
		public String exception(Exception e, Model model) {
			
			model.addAttribute("errorMessage", e.getMessage());
			
			return "common/adminError";
		}
	
	
}


