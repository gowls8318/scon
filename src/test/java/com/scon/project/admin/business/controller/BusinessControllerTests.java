package com.scon.project.admin.business.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.scon.project.admin.business.model.dto.BusinessDTO;
import com.scon.project.config.SconApplication;

@SpringBootTest
@ContextConfiguration(classes = {SconApplication.class})
public class BusinessControllerTests {
	
	@Autowired
	private BusinessController businessController;
	private MockMvc mockMvc;
	
	@Test
	@Disabled
	@DisplayName("의존성 주입 테스트")
	public void testInit() {
		
		assertNotNull(businessController);
		assertNotNull(mockMvc);
	}
	
	@BeforeEach
	public void setUp() {
		
		mockMvc = MockMvcBuilders.standaloneSetup(businessController).build();
	}

// success
	@Test
	@DisplayName("교육원 정보 조회용 컨트롤러 테스트")
	public void testBusinessInfo() throws Exception {
		
		
		// when & then
		mockMvc.perform(MockMvcRequestBuilders.get("/admin/business/businessInfo"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.forwardedUrl("admin/business/businessInfo"))
				.andDo(MockMvcResultHandlers.print());
	}
// success
/*	@Test
	@DisplayName("교육원 정보 입력용 컨트롤러 테스트")
	public void testInsertBusinessInfo() throws Exception {
		
		BusinessDTO bus = new BusinessDTO();
		bus.setBusCode(12345);
		bus.setBusNum(1231212345);
		bus.setBusTitle("SCON");
		bus.setBusRep("김원장");
		bus.setBusPhone("01012345678");
		bus.setBusFax("0212345678");
		bus.setBusAdr("서울시 강남구 역삼동");
		bus.setBusHompy("https://www.scon.ac.kr");
		
		
		// when & then
		mockMvc.perform(MockMvcRequestBuilders.get("/admin/business/businessInfo"))
				.andExpect(MockMvcResultMatchers.status().is3xxRedirection())
				.andExpect(MockMvcResultMatchers.redirectedUrl("/admin/business/businessInfo"))
				.andDo(MockMvcResultHandlers.print());
	}	*/

}
