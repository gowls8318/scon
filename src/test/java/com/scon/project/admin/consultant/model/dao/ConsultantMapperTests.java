package com.scon.project.admin.consultant.model.dao;

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
import com.scon.project.config.SconApplication;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@ContextConfiguration(classes = {SconApplication.class})
@Slf4j
public class ConsultantMapperTests {

	@Autowired
	private ConsultantMapper consultantMapper;
	
	// success
	@Test
	@Disabled
	@DisplayName("매퍼 인터페이스 의존성 주입 테스트")
	public void testInit() {
		
		assertNotNull(consultantMapper);
	}
	
	// success
	@Test
	@Disabled
	@DisplayName("상담 신청 내역 조회용 매퍼 테스트")
	public void testSelectConsultantHope() {
		
		// when
		List<ConsultantDTO> consultantHopeList = consultantMapper.selectAllConsultantHopeList();
		
		// then
		assertNotNull(consultantHopeList);		
	}
	
	// success
	@Test
	@Disabled
	@DisplayName("상담 신청 상세 조회용 매퍼 테스트")
	public void testSelectConsultantHopeDetail() {
		
		// given
		// DB에 있는 글 번호
		int no = 22;
		
		// when
		ConsultantDTO consultantHopeDetail = consultantMapper.selectConsultantHopeDetail(no);
		
		log.info("상담 신청 상세 조회 : {}", consultantHopeDetail);
		
		// then
		assertNotNull(consultantHopeDetail);
	}
	
	// success
	@Test
	@Disabled
	@DisplayName("상담 일지 내역 조회용 매퍼 테스트")
	public void testSelectConsultantList() {
		
		// when
		List<ConsultantDTO> consultantList = consultantMapper.selectAllConsultantList();
		
		// then
		assertNotNull(consultantList);
	}
	
	// success
	@Test
	@Disabled
	@DisplayName("상담 일지 등록용 매퍼 테스트")
	public void testInsertConsultant() {
		
		// given
		ConsultantDTO con = new ConsultantDTO();
		con.setConNo(28);
		con.setConDate("2022-05-05");
		con.setConType("학업 문제");
		con.setConWay("개인 면담");
		con.setConImp(5);
		con.setConContent("개인 면담 진행");
		
		log.info("상담 일지 등록 : {}", con);
		
		// when
		int result = consultantMapper.insertConsultant(con);
		
		// then
		assertEquals(1, result);
	}
	
}
