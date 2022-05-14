package com.scon.project.student.consultant.model.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
public class ConsultantHopeMapperTests {

	@Autowired
	private ConsultantHopeMapper consultantHopeMapper;
	
	// success
	@Test
	@Disabled
	@DisplayName("매퍼 인터페이스 의존성 주입 테스트")
	public void testInit() {
		
		assertNotNull(consultantHopeMapper);
	}
	
	// success
//	@Test
//	@Disabled
//	@DisplayName("상담 신청 내역 조회용 매퍼 테스트")
//	public void testSelectConsultant() {
//		
//		// when
//		List<ConsultantDTO> consultantList = consultantHopeMapper.selectAllConsultantList();
//		
//		// then
//		assertNotNull(consultantList);
//	}
	
	// success
    @Test
    @Disabled
    @DisplayName("상담 신청 내역 조회용(페이징) 매퍼 테스트")
    public void testGetListPaging() {
        
        Criteria cri = new Criteria();
        
        //cri.setPageNo(1);
        cri.setPageNo(2);
                         
        List<ConsultantDTO> consultantList = consultantHopeMapper.selectAllConsultantList(cri);
        
        consultantList.forEach(board -> log.info("" + board));
    }
	
	// success
	@Test
	@Disabled
	@DisplayName("상담 신청 등록용 매퍼 테스트")
	public void testInsertConsultant() {
		
		// given
		ConsultantDTO con = new ConsultantDTO();
		con.setConHDate("2022-05-03");
		con.setConHTime("17:00 ~ 17:50");
		con.setConHTitle("상담 신청 제목");
		con.setConHContent("상담 신청 내용");
		
		// when
		int result = consultantHopeMapper.insertConsultant(con);
		
		// then
		assertEquals(1, result);
	}
	
	// success
	@Test
	@Disabled
	@DisplayName("상담 신청 상세 조회용 매퍼 테스트")
	public void testSelectConsultantDetail() {
		
		// given
		// DB에 있는 글 번호
		int no = 22;
		
		// when
		ConsultantDTO consultantDetail = new ConsultantDTO();
		consultantDetail = consultantHopeMapper.selectConsultantDetail(no);
		
		log.info("상담 신청 상세 조회 : {}", consultantDetail);
		
		// then
		assertNotNull(consultantDetail);
	}
	
	// success
	@Test
	@Disabled
	@DisplayName("상담 신청 수정용 매퍼 테스트")
	public void testUpdateConsultant() {
		
		// given
		// DB에 있는 글 번호
		ConsultantDTO con = new ConsultantDTO();
		con.setNo(22);
		con.setConHDate("2022-05-09");						// 22/05/05
		con.setConHTime("10:00 ~ 10:50");					// 15:00 ~ 15:50
		con.setConHTitle("상담 신청 테스트 제목 수정");			// 상담 신청 테스트
		con.setConHContent("상담 신청 테스트 내용 수정입니다.");	// 상담 신청 테스트 입니다.
		
		// when
		int result = consultantHopeMapper.modifyConsultant(con);
		
		// then
		assertEquals(1, result);
	}
	
	// success
	@Test
	@Disabled
	@DisplayName("상담 신청 삭제용 매퍼 테스트")
	public void testDeleteConsultant() {
		
		// given
		// DB에 있는 글 번호
		int no = 40;
		
		// when
		int result = consultantHopeMapper.deleteConsultant(no);
		
		// then
		assertEquals(1, result);
	}
	
}
