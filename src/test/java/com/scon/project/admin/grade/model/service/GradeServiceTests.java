package com.scon.project.admin.grade.model.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.scon.project.admin.grade.model.dto.GradeDTO;
import com.scon.project.config.SconApplication;

@SpringBootTest
@ContextConfiguration(classes = {SconApplication.class})
public class GradeServiceTests {
	
	/* 서비스 로직에 대한 테스트를 하는 곳 */
	
	//의존성 주입
	@Autowired
	private GradeService gradeService;
	
	//의존성 주입 테스트
	@Test
	public void testInint() {
		assertNotNull(gradeService);
	}
	
	
	@Test
	public void 전체_성적_조회용_메소드_테스트() {
		
		//given
		
		//when
		List<GradeDTO> gradeList = gradeService.findAllGrade();
		
		//then
		assertNotNull(gradeList);
	}
	
	
	
	
	
	
	
}
