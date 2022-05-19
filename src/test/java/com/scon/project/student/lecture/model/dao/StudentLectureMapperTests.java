package com.scon.project.student.lecture.model.dao;

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
public class StudentLectureMapperTests {

	@Autowired
	private StudentLectureMapper studentLectureMapper;
	
	// success
	@Test
	@Disabled
	@DisplayName("매퍼 인터페이스 의존성 주입 테스트")
	public void testInit() {
		
		assertNotNull(studentLectureMapper);
	}
	
	// success
//	@Test
//	@Disabled
//	@DisplayName("수강 내역 조회용 매퍼 테스트")
//	public void testSelectLecture() {
//		
//		// when
//		List<LectureDTO> lectureList = studentLectureMapper.selectAllLectureList();
//		
//		// then
//		assertNotNull(lectureList);
//	}
	
	// success
	@Test
	@Disabled
	@DisplayName("수강 내역 조회용(페이징) 매퍼 테스트")
	public void testSelectLecture() {
		
		Criteria cri = new Criteria();
        
        cri.setPageNo(1);
		
		// when
		List<LectureDTO> lectureList = studentLectureMapper.selectAllLectureList(cri);
		
		lectureList.forEach(board -> log.info("" + board));
		
		// then
		assertNotNull(lectureList);
	}
	
}
