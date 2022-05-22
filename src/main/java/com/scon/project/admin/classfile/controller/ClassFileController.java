package com.scon.project.admin.classfile.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.scon.project.admin.Class.dto.ClassDTO;
import com.scon.project.admin.classfile.dto.ClassFileDTO;
import com.scon.project.admin.classfile.dto.TaskFileDTO;
import com.scon.project.admin.classfile.service.ClassFileService;
import com.scon.project.common.paging.Criteria;
import com.scon.project.common.paging.Pagination;
import com.scon.project.member.model.dto.MemberDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j // log테스트
@Controller
@RequestMapping("/admin")
public class ClassFileController {

	private ClassFileService classFileService;
	private MessageSource messageSource;
	// 파일업로드에 필요한 필드
	private String uploadFilesPath;

	@Autowired
	public ClassFileController(ClassFileService classFileService,
			@Value("${custom.path.upload-files}") String uploadFilesPath, MessageSource messageSource) {

		this.classFileService = classFileService;
		this.messageSource = messageSource;
		this.uploadFilesPath = uploadFilesPath;
		// 업로드 파일패스 생성자 추가 필요 + 매개변수에도 작성

	}

	@Autowired
	ResourceLoader resourceLoader;

	// 강의첨부자료 조회
	@GetMapping("/ClassFileList")
	public ModelAndView ClassFileList(@ModelAttribute Criteria cri, ModelAndView mv) {

		List<MemberDTO> memberList = classFileService.selectMember();
		List<ClassDTO> classNameList = classFileService.selectClassName();
		List<ClassFileDTO> classFileList = classFileService.selectClassFileList(cri);
		List<TaskFileDTO> fileList = classFileService.selectFiles(cri); 
		
		mv.addObject("memberList", memberList);
		mv.addObject("classNameList", classNameList);
		mv.addObject("classFileList", classFileList);
		mv.addObject("fileList", fileList);

		
		  log.info("강의첨부파일리스트 : {}", classFileList); 
		  log.info("강사리스트: {}",memberList); 
		  log.info("강의명리스트: {}", classNameList);
		  log.info("첨부파일정보: {}", fileList);
		


		int total = classFileService.total(cri);
		Pagination page = new Pagination(cri, total);
		mv.addObject("page", page);

		mv.setViewName("admin/classFile/ClassFileList");

		return mv;

	}

	// 강의첨부자료 등록 (GET) - OK
	@GetMapping("/registClassFile")
	public String registClassFile(Model model) { //ModelAndView로 받아오는거 아님

		// 여기에 mapper.xml에도 동일하게 있어야함
		List<MemberDTO> memberList = classFileService.selectMember();
		List<ClassDTO> classNameList = classFileService.selectClassName();

		model.addAttribute("memberList", memberList);
		model.addAttribute("classNameList", classNameList);

		log.info("memberList : {}", memberList);
		log.info("classNameList : {} ", classNameList);

		return "/admin/classFile/insertClassFile";
	}

	// 강의첨부자료 등록(POST)
	@PostMapping("/registClassFile")
	@ResponseBody
	public String classFileUpload(@RequestParam(value="clsfile") List<MultipartFile> multiFiles, 
			@ModelAttribute ClassFileDTO classFile) throws Exception {
		//@RequestParam int clsId,
		//HttpServletRequest request, 
		
		String page = "";
		log.info("강의첨부자료리스트정보 : {} ", classFile); 
		
		
		File mkdir = new File(uploadFilesPath + "\\classFiles");
		if(!mkdir.exists()) {
			mkdir.mkdirs();
		}
		
		List<TaskFileDTO> fileList2 = new ArrayList<>();
		
		for(int i = 0; i < multiFiles.size(); i++) {
			TaskFileDTO fileInfo = new TaskFileDTO();
			
			//파일명 변경 처리
			String originFileName = multiFiles.get(i).getOriginalFilename();
			String ext = originFileName.substring(originFileName.lastIndexOf("."));
			String savedName = UUID.randomUUID().toString().replace("-", "") + ext;
			
			
			// 파일 정보 FileDTO의 setter에 담기
			fileInfo.setFileOrginName(originFileName);
			fileInfo.setFileSaveName(savedName);
			fileInfo.setFilePath("/uploadFiles/classFiles/");
			fileInfo.setFileType(ext);

			/* List 타입에 담기 */
			fileList2.add(fileInfo);
			
		}
		/* TaskFileDTO에 담기 */
		classFile.setFileList2(fileList2);

		log.info("첨부된파일정보 : {} ", fileList2);

		//task 객체 안에 파일 정보까지 담아서 1.테스크 테이블 insert 2.파일 테이블 insert 3.테스트 파일 테이블 insert 3개가 잘 됐을 때 로직 성공
		// 3개 DAO에 선언 된 메소드를 호출 
		int result = classFileService.registClassFile(classFile);

		if(result > 0) {
			page = "/admin/ClassFileList";
		} else {
			throw new Exception("게시글 등록 실패!");
		}

		try {
		
			// 파일을 저장한다. 
			for(int i = 0; i < fileList2.size(); i++) {
				multiFiles.get(i).transferTo(new File(uploadFilesPath + "\\classFiles\\" + fileList2.get(i).getFileSaveName()));
			}
			
			
		} catch (IllegalStateException | IOException e) {
			
			// 업로드 실패 시 이전에 저장 된 파일도 삭제한다. 
			for(int i = 0; i < fileList2.size(); i++) {
				new File(uploadFilesPath + "\\classFiles\\" + fileList2.get(i).getFileSaveName()).delete();
			}
			
		}
		return page;

	}
	
	//강의삭제
	@GetMapping("/deleteClassFile")
	public String deleteClassFile(@RequestParam String fileId, RedirectAttributes redirectAttr) throws Exception { 
		 
		/* log.debug("강의 삭제 요청 id : {}", clsId); */
		log.debug("게시글 삭제 요청 id : {}", fileId);
		
		classFileService.deleteClassFile(fileId);
		/* redirectAttr.addAttribute("clsId", clsId); */
		return "redirect:/admin/ClassFileList"; // clsId
	}
}
