package com.scon.project.student.lecture.model.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.scon.project.admin.lecture.model.dto.LectureDTO;
import com.scon.project.common.paging.Criteria;
import com.scon.project.config.SconApplication;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@ContextConfiguration(classes = {SconApplication.class})
@Slf4j
public class StudentLectureServiceTests {

	@Autowired
	private StudentLectureService studentLectureService;
	
	// success
	@Test
	@Disabled
	@DisplayName("의존성 주입 테스트")
	public void testInit() {
		
		assertNotNull(studentLectureService);
	}
	
	// success
//	@Test
//	@Disabled
//	@DisplayName("수강 내역 조회용 서비스 메소드 테스트")
//	public void testSelectLecture() throws Exception {
//		
//		// when
//		List<LectureDTO> lectureList = studentLectureService.selectAllLectureList();
//		
//		// then
//		assertNotNull(lectureList);
//	}
	
	// success
	@Test
	@Disabled
	@DisplayName("수강 내역 조회용(페이징) 서비스 메소드 테스트")
	public void testSelectLecture() throws Exception {
		
		Criteria cri = new Criteria();
        
	    cri.setPageNo(1);
		
		// when
		List<LectureDTO> lectureList = studentLectureService.selectAllLectureList(cri);
		
		lectureList.forEach(board -> log.info("" + board));
		
		// then
		assertNotNull(lectureList);
	}
	
}
