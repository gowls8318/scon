package com.scon.project.admin.lecture.model.service;

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
import com.scon.project.member.model.dto.MemberDTO;

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
	@DisplayName("강의 조회용 서비스 메소드 테스트")
	public void testSelectClass() throws Exception {
		
		// when
		List<ClassDTO> classList = lectureService.selectAllClassList();
		
		// then
		assertNotNull(classList);
	}
	
	// success
	@Test
	@Disabled
	@DisplayName("원생 조회용 서비스 메소드 테스트")
	public void testSelectStudent() throws Exception {
		
		// when
		List<MemberDTO> memberList = lectureService.selectAllMemberList();
		
		// then
		assertNotNull(memberList);
	}
	
	// success
	@Test
	@Disabled
	@DisplayName("수강 등록용 서비스 메소드 테스트")
	public void testInsertLecture() throws Exception {
		
		// given
		LectureDTO lec = new LectureDTO();
		lec.setMemberId("user03");
		lec.setClsId(1);
		lec.setLecPay(100000);
		lec.setLecDiscount("친구 추천 이벤트");
		
		// when
		int result = lectureService.insertLecture(lec);
		
		// then
		assertNotNull(result);
	}
	
	// success
	@Test
	@Disabled
	@DisplayName("수강 정보 조회용 서비스 메소드 테스트")
	public void testSelectLectureDetail() throws Exception {
		
		// given
		int no = 46;
		
		// when
		LectureDTO lectureDetail = new LectureDTO();
		lectureDetail = lectureService.selectLectureDetail(no);
		
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
		lec.setNo(15);
		lec.setLecStatus("수강 완료");
		lec.setAccStatus("수납");
		lec.setAccDate("2022-05-12");
		lec.setAccOption("신용카드");
		
		// when
		int result = lectureService.modifyLecture(lec);
		
		// then
		assertEquals(1, result);
	}
	
	// success
	@Test
	@Disabled
	@DisplayName("수강 삭제용 서비스 메소드 테스트")
	public void testDeleteLecture() throws Exception {
		
		// given
		int no = 12;
		
		// when
		int result = lectureService.deleteLecture(no);
		
		// then
		assertNotNull(result);
	}
	
}
