package com.scon.project.admin.grade.model.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.scon.project.admin.Class.dto.ClassDTO;
import com.scon.project.admin.grade.model.dao.GradeMapper;
import com.scon.project.admin.grade.model.dto.GradeDTO;
import com.scon.project.config.SconApplication;
import com.scon.project.member.model.dto.MemberDTO;

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
	
	/* 전체 성적 조회  (complete) */
	@Test
	@Disabled //지금 테스트 안할거라 어노테이션 붙여 놨음 (테스트 제외)
	public void 전체_성적_조회용_매퍼_테스트() {
		//given
		int classId = 1;
		
		//when
		List<GradeDTO> gradeList = gradeMapper.findAllGrade(classId);
		
		//then 
		assertNotNull(gradeList);
	}
	
	/* 성적 입력 학생 조회 */
	@Test
	public void  성적_입력_학생_조회_매퍼_테스트() {
		
		//given
		int classId = 1;
		
		//when
		List<MemberDTO> memberList = gradeMapper.findAllStudent(classId);
				
		//then
		assertNotNull(memberList);
	}

	/* 성적 입력(insert) 테스트*/
	@Test
	@Disabled
	public void 성적_입력_매퍼_테스트() {
		
		//given
		GradeDTO grade = new GradeDTO();
		//gradeId, clsId는 시퀀스
		grade.setMemberId("user02");
		
		String date = "2022-05-04";
		java.sql.Date date2 = java.sql.Date.valueOf(date);
		grade.setGradeDate(date2);
		grade.setGrade(100);
		

		//when
		int result = gradeMapper.insertGradeList(grade);

		//then
		assertEquals(1, result);
		
		
		
//		List<GradeDTO> gradeList = new ArrayList<>();
		//성적 1
	/*	GradeDTO grade1 = new GradeDTO();
		grade1.setMemberId("user01");
				
		String date1 = "2022-05-03";
		java.sql.Date date2 = java.sql.Date.valueOf(date1);
		grade1.setGradeDate(date2);
		grade1.setGrade(100);
				
		//성적 2
		GradeDTO grade2 = new GradeDTO();
		grade2.setMemberId("user01");
		
		String date3 = "2022-05-03";
		java.sql.Date date4 = java.sql.Date.valueOf(date3);
		
		//List에 담기
		gradeList.add(grade1);
		gradeList.add(grade2); */
	}
	
	
	
	
	
	
	
	
}
