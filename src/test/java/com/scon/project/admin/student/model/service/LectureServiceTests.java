package com.scon.project.admin.student.model.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.scon.project.admin.Class.dto.ClassDTO;
import com.scon.project.admin.student.model.dto.LectureDTO;
import com.scon.project.config.SconApplication;

@SpringBootTest
@ContextConfiguration(classes = {SconApplication.class})
public class LectureServiceTests {

	@Autowired
	private LectureService lectureService;
	
	// success
	@Test
	@Disabled
	@DisplayName("의존성 주입 테스트")
	public void testInit() {
		
		assertNotNull(lectureService);
	}
	
	// success
	@Test
	@Disabled
	@DisplayName("수강 내역 조회용 서비스 메소드 테스트")
	public void testSelectLecture() throws Exception {
		
		// when
		List<LectureDTO> lectureList = lectureService.selectAllLectureList();
		
		// then
		assertNotNull(lectureList);
	}
	
	// success
	@Test
	@Disabled
	@DisplayName("전체 강의 조회용 서비스 메소드 테스트")
	public void testFindClass() throws Exception {
		
		// when
		List<ClassDTO> classList = lectureService.findAllClassList();
		
		// then
		assertNotNull(classList);
	}
	
	// success
	@Test
	@Disabled
	@DisplayName("수강 등록용 서비스 메소드 테스트")
	public void testInsertLecture() throws Exception {
		
		// given
		LectureDTO lec = new LectureDTO();
		lec.setClsId(21);
		lec.setLecPay(250000);
		
		// when
		int result = lectureService.insertLecture(lec);
		
		// then
		assertNotNull(result);
	}
	
}
