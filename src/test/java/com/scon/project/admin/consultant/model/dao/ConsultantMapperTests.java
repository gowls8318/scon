package com.scon.project.admin.consultant.model.dao;

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

@SpringBootTest
@ContextConfiguration(classes = {SconApplication.class})
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
	
}
