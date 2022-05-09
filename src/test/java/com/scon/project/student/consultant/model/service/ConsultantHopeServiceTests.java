package com.scon.project.student.consultant.model.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.scon.project.admin.consultant.model.dto.ConsultantDTO;
import com.scon.project.config.SconApplication;

@SpringBootTest
@ContextConfiguration(classes = {SconApplication.class})
public class ConsultantHopeServiceTests {

	@Autowired
	private ConsultantHopeService consultantHopeService;
	
	// success
	@Test
	@Disabled
	@DisplayName("의존성 주입 테스트")
	public void testInit() {
		
		assertNotNull(consultantHopeService);
	}
	
	// success
	@Test
	@Disabled
	@DisplayName("상담 신청 내역 조회용 서비스 메소드 테스트")
	public void testSelectConsultant() throws Exception {
		
		// when
		List<ConsultantDTO> consultantList = consultantHopeService.selectAllConsultantList();
		
		// then
		assertNotNull(consultantList);
	}
	
	// success
	@Test
	@Disabled
	@DisplayName("상담 신청 등록용 서비스 메소드 테스트")
	public void testInsertConsultant() throws Exception {
		
		// given
		ConsultantDTO con = new ConsultantDTO();
		con.setConHDate("2022-05-08");
		con.setConHTime("17:00 ~ 17:50");
		con.setConHTitle("상담 신청 제목");
		con.setConHContent("상담 신청 내용");
		
		// when
		boolean result = consultantHopeService.insertConsultant(con);
		
		// then
		assertTrue(result);
	}
	
	// success
	@Test
	@Disabled
	@DisplayName("상담 신청 상세 조회용 서비스 메소드 테스트")
	public void testSelectConsultantDetail() throws Exception {
		
		// given
		int no = 22;
		
		// when
		ConsultantDTO consultantDetail = new ConsultantDTO();
		consultantDetail = consultantHopeService.selectConsultantDetail(no);
		
		// then
		assertNotNull(consultantDetail);
	}
	
	// success
	@Test
	@Disabled
	@DisplayName("상담 신청 수정용 서비스 메소드 테스트")
	public void testUpdateConsultant() throws Exception {
		
		// given
		// DB에 있는 글 번호
		ConsultantDTO con = new ConsultantDTO();
		con.setNo(22);
		con.setConHDate("2022-05-09");						// 22/05/05
		con.setConHTime("10:00 ~ 10:50");					// 15:00 ~ 15:50
		con.setConHTitle("상담 신청 테스트 제목 수정");			// 상담 신청 테스트
		con.setConHContent("상담 신청 테스트 내용 수정입니다.");	// 상담 신청 테스트 입니다.
		
		// when
		int result = consultantHopeService.modifyConsultant(con);
		
		// then
		assertNotNull(result);
	}
	
	// success
	@Test
	@Disabled
	@DisplayName("상담 신청 삭제용 서비스 메소드 테스트")
	public void testDeleteConsultant() throws Exception {
		
		// given
		// DB에 있는 글 번호
		int no = 40;
		
		// when
		int result = consultantHopeService.deleteConsultant(no);
		
		// then
		assertNotNull(result);
	}
	
}
