package com.scon.project.admin.business.model.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.scon.project.admin.business.model.dto.BusinessDTO;
import com.scon.project.config.SconApplication;


@SpringBootTest
@ContextConfiguration(classes = {SconApplication.class})
public class BusinessMapperTests {
	
	@Autowired
	private BusinessMapper businessMapper;
	
	// success
	@Test
	@Disabled
	@DisplayName("매퍼 인터페이스 의존성 주입 테스트")
	public void testInit() {
		
		assertNotNull(businessMapper);
	}

	// success
	
	@Test
	@Disabled
	@DisplayName("교육원 정보 조회용 매퍼 테스트")
	public void testSelectBusinessInfo() {
		
		
		// when
		BusinessDTO selectBusinessInfo = businessMapper.selectBusinessInfo();
		
		// then
		assertNotNull(selectBusinessInfo);
	}
	// success
	

	
	
	


}
