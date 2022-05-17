package com.scon.project.admin.classfile.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

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

		List<ClassFileDTO> classFileList = classFileService.selectClassFileList(cri);

		log.info("classFileList : {}", classFileList);

		mv.addObject("classFileList", classFileList);

		int total = classFileService.total(cri);
		Pagination page = new Pagination(cri, total);
		mv.addObject("page", page);

		mv.setViewName("admin/classFile/ClassFileList");

		return mv;

	}

	// 강의첨부자료 등록 (GET) - OK
	@GetMapping("/registClassFile")
	public String registClassFile(Model model) { //ModelAndView (X)

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
	public String classFileUpload(@RequestParam(value="classFile", required = false) List<MultipartFile> multiFiles, 
			HttpServletRequest request, @ModelAttribute ClassFileDTO classFile) throws Exception {
		//@RequestParam int clsId,
		
		log.info("classFile : {} ", classFile);
		String page = "";
		
		
		File mkdir = new File(uploadFilesPath + "\\classFiles");
		if(!mkdir.exists()) {
			mkdir.mkdirs();
		}
		
		List<TaskFileDTO> fileList = new ArrayList<>();
		
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
			fileList.add(fileInfo);
			
		}
		/* TaskFileDTO에 담기 */
		classFile.setFileList(fileList);

		log.info("fileList : {} ", fileList);

		//task 객체 안에 파일 정보까지 담아서 1.테스크 테이블 insert 2.파일 테이블 insert 3.테스트 파일 테이블 insert 3개가 잘 됐을 때 로직 성공
		// 3개 DAO에 선언 된 메소드를 호출 
		int result = classFileService.registClassFile(classFile);

		if(result > 0) {
			page = "redirect:/admin/ClassFileList";
		} else {
			throw new Exception("게시글 등록 실패!");
		}

		try {
		
			// 파일을 저장한다. 
			for(int i = 0; i < fileList.size(); i++) {
				multiFiles.get(i).transferTo(new File(uploadFilesPath + "\\classFiles\\" + fileList.get(i).getFileSaveName()));
			}
			
			
		} catch (IllegalStateException | IOException e) {
			
			// 업로드 실패 시 이전에 저장 된 파일도 삭제한다. 
			for(int i = 0; i < fileList.size(); i++) {
				new File(uploadFilesPath + "\\classFiles\\" + fileList.get(i).getFileSaveName()).delete();
			}
			
		}
		return page;

		/*
		 * multipart로 전송 된 request에 대한 인코딩 처리를 해주어야 하는데 일반 인코딩 필터보다 구현하기 어려우므로 스프링에서
		 * 제공하는 인코딩 필터를 사용한다. (web.xml) String singleFileDescription =
		 * request.getParameter("singleFileDescription");
		 * System.out.println("singleFile " + singleFile);
		 * System.out.println("singleFileDescription : " + singleFileDescription);
		 * 
		 * 파일을 저장할 경로 설정 (프로젝트에 하나의 경로를 사용) String root =
		 * request.getSession().getServletContext().getRealPath("resources");
		 * 
		 * System.out.println("root : " + root); //원하는것: 실제path
		 * 
		 * String filePath = root + "\\uploadFiles"; //경로뒤에 "경로이름 붙임"
		 * 
		 * //자연님 보내주신 코드로 수정하기 (내경로로) File mkdir = new File(filePath); //1. 실제 존재하는 경로가
		 * 아니라면 exists를 물어보고 if(!mkdir.exists()) { mkdir.mkdirs(); //2. 경로를 생성해준다 }
		 * 
		 * 
		 * 파일명을 규칙에 맞춰서 변경처리를 해준다 String originFileName =
		 * singleFile.getOriginalFilename(); //파일에 대한 정보 : 매개변수에 선언했던 mulifiletype에
		 * 파일로부터 얻어 올 수 있다. String ext =
		 * originFileName.substring(originFileName.lastIndexOf(".")); // 보존해야하는것은
		 * '.'뒤에오는 확장자 (.png인지 .pdf인지..) -> 올바르게 파일 해석 가능
		 * 
		 * 확장자 앞쪽 부분은 변경해주어야 한다 String savedName =
		 * UUID.randomUUID().toString().replace("-", "") + ext;
		 * 
		 * 
		 * 필요한 정보 Map에 담기 Map<String, String> file = new HashMap<>();
		 * file.put("originFileName", originFileName); file.put("savedName", savedName);
		 * file.put("filePath", filePath);
		 * 
		 * 
		 * classFile.setFileOrginName(originFileName);
		 * classFile.setFileSaveName(savedName); classFile.setFilePath(filePath); //2.
		 * 자연님이 공유해주신것 classFile.setFileType(ext); classFile.setClsId(clsId);
		 * classFile.setClsName(clsName);
		 * 
		 * int result = ClassFileService.registClassFile
		 * 
		 * 
		 * //파일경로 : 자연님 조언 얻기
		 * 
		 * 파일을 서버 특정 위치에 저장한다 try { singleFile.transferTo(new File(filePath + "\\" +
		 * savedName)); // 이게 바로 파일을 저장한다는 행위에 해당한다. model.addAttribute("message",
		 * "파일 업로드 성공!"); } catch (IllegalStateException | IOException e) {
		 * model.addAttribute("message", "파일 업로드 실패!ㅜㅜ"); }
		 * 
		 * return "admin/classFile/classFileList"; //클래스파일리스트로 리턴한다.
		 * 
		 */

	}

}
