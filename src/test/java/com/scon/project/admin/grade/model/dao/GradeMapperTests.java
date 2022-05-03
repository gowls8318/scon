package com.scon.project.admin.grade.model.dao;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.scon.project.admin.grade.model.dao.GradeMapper;
import com.scon.project.admin.grade.model.dto.GradeDTO;
import com.scon.project.config.SconApplication;

@SpringBootTest
@ContextConfiguration(classes = {SconApplication.class})
public class GradeMapperTests {

	//의존성 주입
	@Autowired
	private GradeMapper gradeMapper;
	
	@Test
	public void 매퍼_인터페이스_의존성_주입_테스트() {
		
		assertNotNull(gradeMapper);
	}
	
	/* 전체 성적 조회 */
	@Test
	public void 전체_성적_조회용_매퍼_테스트() {
		//given
		
		//when
		List<GradeDTO> gradeList = gradeMapper.findAllGrade();
		
		//then 
		assertNotNull(gradeList);
	}

	/* 성적 입력(insert) 테스트*/
	@Test
	public void 성적_입력_매퍼_테스트() {
		
		//given
		
		//when
		
		//then
	}
	
	
	
	
	
	
	
	
}
