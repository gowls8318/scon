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
import com.scon.project.member.model.dto.MemberDTO;

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
	@DisplayName("강의 조회용 매퍼 테스트")
	public void testSelectClass() {
		
		// when
		List<ClassDTO> classList = lectureMapper.selectAllClassList();
		
		// then
		assertNotNull(classList);
	}
	
	// success
	@Test
	@Disabled
	@DisplayName("원생 조회용 매퍼 테스트")
	public void testSelectStudent() {
		
		// when
		List<MemberDTO> memberList = lectureMapper.selectAllMemberList();
		
		// then
		assertNotNull(memberList);
	}
	
	// success
	@Test
	@Disabled
	@DisplayName("수강 등록용 매퍼 테스트")
	public void testInsertLecture() {
		
		// given
		LectureDTO lec = new LectureDTO();
		lec.setMemberId("user02");
		lec.setClsId(1);
		lec.setLecPay(100000);
		lec.setLecDiscount("오픈 이벤트");
		
		// when
		int result = lectureMapper.insertLecture(lec);
		
		// then
		assertEquals(1, result);
	}
	
	// success
	@Test
	@Disabled
	@DisplayName("수강 정보 조회용 매퍼 테스트")
	public void testSelectLectureDetail() {
		
		// given
		int no = 46;
		
		// when
		LectureDTO lectureDetail = new LectureDTO();
		lectureDetail = lectureMapper.selectLectureDetail(no);
		
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
		lec.setNo(41);
		lec.setLecStatus("수강 완료");
		lec.setAccStatus("수납");
		lec.setAccDate("2022-05-12");
		lec.setAccOption("신용카드");
		
		// when
		int result = lectureMapper.modifyLecture(lec);
		
		// then
		assertEquals(1, result);
	}
	
	// success
	@Test
	@Disabled
	@DisplayName("수강 삭제용 매퍼 테스트")
	public void testDeleteLecture() {
		
		// given
		int no = 13;
		
		// when
		int result = lectureMapper.deleteLecture(no);
		
		// then
		assertNotNull(result);
	}
	
//	@Test
//	@DisplayName("환불 등록용 매퍼 테스트")
//	public void testInsertRefund() {
//		
//		// given
//		int no = 48;
//		
//		// when
//		int result = lectureMapper.insertRefund(no);
//		
//		// then
//		assertNotNull(result);
//	}
	
}
