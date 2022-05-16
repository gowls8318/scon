package com.scon.project.admin.consultant.model.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.scon.project.admin.consultant.model.dto.ConsultantDTO;
import com.scon.project.common.paging.Criteria;
import com.scon.project.config.SconApplication;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@ContextConfiguration(classes = {SconApplication.class})
@Slf4j
public class ConsultantServiceTests {

	@Autowired
	private ConsultantService consultantService;
	
	// success
	@Test
	@Disabled
	@DisplayName("의존성 주입 테스트")
	public void testInit() {
		
		assertNotNull(consultantService);
	}
	
	// success
//	@Test
//	@Disabled
//	@DisplayName("상담 신청 내역 조회용 서비스 메소드 테스트")
//	public void testSelectConsultantHope() throws Exception {
//		
//		// when
//		List<ConsultantDTO> consultantHopeList = consultantService.selectAllConsultantHopeList();
//		
//		// then
//		assertNotNull(consultantHopeList);
//	}
	
	// success
	@Test
	@DisplayName("상담 신청 내역 조회용(페이징) 서비스 메소드 테스트")
	public void testSelectConsultantHope() throws Exception {
		
		Criteria cri = new Criteria();
        
      //cri.setPageNo(1);
        cri.setPageNo(2);
        
        // when
        List<ConsultantDTO> consultantHopeList = consultantService.selectAllConsultantHopeList(cri);
        
        consultantHopeList.forEach(board -> log.info("" + board));
        
        // then
        assertNotNull(consultantHopeList);
	}
	
	// success
	@Test
	@Disabled
	@DisplayName("상담 신청 상세 조회용 서비스 메소드 테스트")
	public void testSelectConsultantHopeDetail() throws Exception {
		
		// given
		int no = 22;
		
		// when
		ConsultantDTO consultantHopeDetail = consultantService.selectConsultantHopeDetail(no);
		
		// then
		assertNotNull(consultantHopeDetail);
	}
	
	// success
//	@Test
//	@Disabled
//	@DisplayName("상담 일지 내역 조회용 서비스 메소드 테스트")
//	public void testSelectConsultantList() throws Exception {
//		
//		// when
//		List<ConsultantDTO> consultantList = consultantService.selectAllConsultantList();
//		
//		// then
//		assertNotNull(consultantList);
//	}
	
	@Test
	@DisplayName("상담 일지 내역 조회용(페이징) 서비스 메소드 테스트")
	public void testSelectConsultantList() throws Exception {
		
		Criteria cri = new Criteria();
        
	    cri.setPageNo(1);
	        
		// when
		List<ConsultantDTO> consultantList = consultantService.selectAllConsultantList(cri);
		
		consultantList.forEach(board -> log.info("" + board));
		
		// then
		assertNotNull(consultantList);
	}
	
	// success
	@Test
	@Disabled
	@DisplayName("상담 일지 등록용 서비스 메소드 테스트")
	public void testInsertConsultant() throws Exception {
		
		// given
		ConsultantDTO con = new ConsultantDTO();
		con.setNo(54);
		con.setConDate("2022-05-05");
		con.setConType("학업 문제");
		con.setConWay("개인 면담");
		con.setConImp("★★★★★");
		con.setConContent("개인 면담 진행");
		
		// when
		int result = consultantService.insertConsultant(con);
		
		// then
		assertEquals(1, result);
	}
	
	// success
	@Test
	@Disabled
	@DisplayName("상담 일지 상세 조회용 서비스 메소드 테스트")
	public void testConsultantDetail() throws Exception {
		
		// given
		int no = 52;
				
		// when
		ConsultantDTO consultantDetail = consultantService.selectConsultantDetail(no);
		
		// then
		assertNotNull(consultantDetail);
	}
	
	// success
	@Test
	@Disabled
	@DisplayName("상담 일지 수정용 서비스 메소드 테스트")
	public void testUpdateConsultant() throws Exception {
		
		// given
		// DB에 있는 글 번호
		ConsultantDTO con = new ConsultantDTO();
		con.setNo(52);
		con.setConType("가정 문제");							// 교우 문제
		con.setConWay("개인 면담");							// 방문 상담
		con.setConImp("★★★★★");							// ★★★★☆
		con.setConContent("상담 일지 내용 가정 문제 입니다.");		// 상담 일지 내용 수정입니다.
		
		// when
		int result = consultantService.modifyConsultant(con);
		
		// then
		assertEquals(1, result);
	}
	
	// success
	@Test
	@Disabled
	@DisplayName("상담 일지 삭제용 서비스 메소드 테스트")
	public void testDeleteConsultant() throws Exception {
		
		// given
		int no = 52;
		
		// when
		int result = consultantService.deleteConsultant(no);
		
		// then
		assertEquals(1, result);
	}

}
