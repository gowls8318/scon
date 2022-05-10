package com.scon.project.admin.lecture.model.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.scon.project.admin.Class.dto.ClassDTO;
import com.scon.project.admin.lecture.model.dto.LectureDTO;
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
	@DisplayName("강의 정보 조회용 서비스 메소드 테스트")
	public void testFindClassDetail() throws Exception {
		
		// when
		List<ClassDTO> classList = lectureService.findClassDetail();
		
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
	
	// success
	@Test
	@Disabled
	@DisplayName("수강 상세 조회용 서비스 메소드 테스트")
	public void testSelectLectureDetail() throws Exception {
		
		// given
		int no = 12;
		
		// when
		LectureDTO lectureDetail = lectureService.selectLectureDetail(no);
		
		// then
		assertNotNull(lectureDetail);
	}
	
	// success
	@Test
	@Disabled
	@DisplayName("수강 수정용 서비스 메소드 테스트")
	public void testUpdateLecture() throws Exception {
		
		// given
		LectureDTO lec = new LectureDTO();
		lec.setNo(12);
		lec.setClsId(21);					// 22
		lec.setLecPay(500000);				// 5000
		lec.setLecStatus("수강중");			// 수강중
		lec.setLecDiscount("오픈 이벤트");		// 오픈 이벤트
		
		// when
		int result = lectureService.modifyLecture(lec);
		
		// then
		assertNotNull(result);
	}
	
}
