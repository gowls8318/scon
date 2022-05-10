package com.scon.project.admin.business.model.service;

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
public class BusinessServiceTests {
	

	@Autowired
	private BusinessService businessService;
	
	// success
	@Test
	@Disabled
	@DisplayName("의존성 주입 테스트")
	public void testInit() {
		
		assertNotNull(businessService);
	}
	
	// success
	@Test
	@DisplayName("교육원 정보 조회용 서비스 메소드 테스트")
	public void 교육원_정보_조회_테스트() {
		
		// given
		
		// when
		BusinessDTO selectBusinessInfo = businessService.selectBusinessInfo();
		
		// then
		assertNotNull(selectBusinessInfo);
	}
	

	
}
