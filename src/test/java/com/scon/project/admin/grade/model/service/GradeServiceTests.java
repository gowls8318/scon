package com.scon.project.admin.grade.model.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.scon.project.admin.grade.model.dto.GradeDTO;
import com.scon.project.config.SconApplication;
import com.scon.project.member.model.dto.MemberDTO;

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
	
	
	/* 성적 조회용 메소드 (complete) */
	@Test
	@Disabled
	public void 전체_성적_조회용_메소드_테스트() {
		
		//given
		int clsId = 1;
		//when
		List<GradeDTO> gradeList = gradeService.findAllGrade(clsId);
		
		//then
		assertNotNull(gradeList);
	}
	
	/*성적 입력 전 학생 조회 테스트 (complete) */
	@Test
	public void 성적_입력_학생_조회_테스트() {
		
		//given

		//given
		int classId = 1;
		
		//when
		List<GradeDTO> memberList = gradeService.findAllStudent(classId);
				
		//then
		assertNotNull(memberList);
	}
	
	
	/* 성적 입력 테스트 */
//	@Test
//	public void 성적_입력_서비스_성공_테스트() throws Exception {
//		//given
//		GradeDTO grade = new GradeDTO();
//		//gradeId, clsId는 시퀀스 (clsId는 임의로 줬음)
//		grade.setClsId(1);
//		grade.setMemberId("user02");
//		
//		String date = "22.05.08";
//		grade.setGradeDate(date);
//		grade.setGrade(100);
//
//		//when
//		//결과값을 true/false로 받기
//		boolean result = gradeService.insertGradeList();
//
//		//then
//		assertTrue(result);
//	}
	
//	@Test
//	@Disabled
//	public void 성적_입력_서비스_실패_테스트() {
//		//given
//		GradeDTO grade = new GradeDTO();
//		//gradeId, clsId는 시퀀스 (clsId는 임의로 줬음)
//		grade.setClsId(1);
//		grade.setMemberId("user02");
//		
//		String date = "22.05.08";
//		grade.setGradeDate(date);
//		grade.setGrade(100);
//		
//		//when & then
//		assertThrows(Exception.class, () -> gradeService.insertGradeList(grade));
//		
//	}
//	
	
	
}
