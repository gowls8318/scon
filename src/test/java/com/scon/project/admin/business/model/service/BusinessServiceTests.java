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
	
	// success
	@Test
	@DisplayName("교육원 정보 입력용 서비스 메소드 테스트")
	public void 교육원_정보_입력_테스트() {
		
		// given
		BusinessDTO bus = new BusinessDTO();
		bus.setBusCode(12345);
		bus.setBusNum(1231212345);
		bus.setBusTitle("SCON");
		bus.setBusRep("김원장");
		bus.setBusPhone("01012345678");
		bus.setBusFax("0212345678");
		bus.setBusAdr("서울시 강남구 역삼동");
		bus.setBusHompy("https://www.scon.ac.kr");
		
		
		// when
		int result = businessService.insertBusinessInfo(bus);
		
		// then
		assertEquals(1, result);
	}
	
}
