package com.scon.project.admin.lecture.model.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
public class LectureMapperTests {

	@Autowired
	private LectureMapper lectureMapper;
	
	// success
	@Test
	@Disabled
	@DisplayName("매퍼 인터페이스 의존성 주입 테스트")
	public void testInit() {
		
		assertNotNull(lectureMapper);
	}
	
	// success
	@Test
	@Disabled
	@DisplayName("수강 내역 조회용 매퍼 테스트")
	public void testSelectLecture() {
		
		// when
		List<LectureDTO> lectureList = lectureMapper.selectAllLectureList();
		
		// then
		assertNotNull(lectureList);
	}
	
	// success
	@Test
	@Disabled
	@DisplayName("전체 강의 조회용 매퍼 테스트")
	public void testFindClass() {
		
		// when
		List<ClassDTO> classList = lectureMapper.findAllClassList();
		
		// then
		assertNotNull(classList);
	}
	
	// success
	@Test
	@Disabled
	@DisplayName("강의 정보 조회용 매퍼 테스트")
	public void testFindClassDetail() {
		
		// when
		List<ClassDTO> classList = lectureMapper.findClassDetail();
		
		// then
		assertNotNull(classList);
	}
	
	// success
	@Test
	@Disabled
	@DisplayName("수강 등록용 매퍼 테스트")
	public void testInsertLecture() {
		
		// given
		LectureDTO lec = new LectureDTO();
		lec.setClsId(21);
		lec.setLecPay(250000);
		
		// when
		int result = lectureMapper.insertLecture(lec);
		
		// then
		assertEquals(1, result);
	}
	
	// success
	@Test
	@Disabled
	@DisplayName("수강 상세 조회용 매퍼 테스트")
	public void testSelectLectureDetail() {
		
		// given
		int no = 12;
		
		// when
		LectureDTO lectureDetail = lectureMapper.selectLectureDetail(no);
		
		// then
		assertNotNull(lectureDetail);
	}
	
	// success
	@Test
	@Disabled
	@DisplayName("수강 수정용 매퍼 테스트")
	public void testUpdateLecture() {
		
		// given
		LectureDTO lec = new LectureDTO();
		lec.setNo(12);
		lec.setClsId(22);					// 1
		lec.setLecPay(5000);				// 400000
		lec.setLecStatus("수강중");			// 수강중
		lec.setLecDiscount("오픈 이벤트");		// 친구 추천 이벤트
		
		// when
		int result = lectureMapper.modifyLecture(lec);
		
		// then
		assertEquals(1, result);
	}
	
}
