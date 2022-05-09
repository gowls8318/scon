package com.scon.project.admin.student.model.dao;

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
import com.scon.project.admin.student.model.dto.LectureDTO;
import com.scon.project.config.SconApplication;
import com.scon.project.student.consultant.model.dao.ConsultantHopeMapperTests;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@ContextConfiguration(classes = {SconApplication.class})
@Slf4j
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
	
}
