package com.scon.project.student.consultant.controller;

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
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.scon.project.config.SconApplication;

@SpringBootTest
@ContextConfiguration(classes = {SconApplication.class})
public class ConsultantHopeControllerTests {

	@Autowired
	private ConsultantHopeController consultantHopeController;
	private MockMvc mockMvc;
	
	// success
	@Test
	@Disabled
	@DisplayName("의존성 주입 테스트")
	public void testInit() {
		
		assertNotNull(consultantHopeController);	// success
		assertNotNull(mockMvc);						// success
	}
	
	@BeforeEach
	public void setUp() {
		
		mockMvc = MockMvcBuilders.standaloneSetup(consultantHopeController).build();
	}
	
	// success
	@Test
	@Disabled
	@DisplayName("상담 신청 내역 조회용 컨트롤러 테스트")
	public void testSelectConsultant() throws Exception {
		
		// when & then
		mockMvc.perform(MockMvcRequestBuilders.get("/student/consultant/consultantList"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.forwardedUrl("student/consultant/consultantList"))
				.andDo(MockMvcResultHandlers.print());		
	}
	
	// success
	@Test
	@Disabled
	@DisplayName("상담 신청 등록용 컨트롤러 테스트")
	public void testInsertConsultant() throws Exception {
		
		// given
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("conHDate", "2022-05-03");
		params.add("conHTime", "17:00 ~ 17:50");
		params.add("conHTitle", "상담 신청 제목");
		params.add("conHContent", "상담 신청 내용");
		
		// when & then
		mockMvc.perform(MockMvcRequestBuilders.post("/student/consultant/insertForm").params(params))
				.andExpect(MockMvcResultMatchers.status().is3xxRedirection())
				.andExpect(MockMvcResultMatchers.flash().attributeCount(1))
				.andExpect(MockMvcResultMatchers.redirectedUrl("/student/consultant/list"))
				.andDo(MockMvcResultHandlers.print());
	}
	
	// success
//	@Test
//	@Disabled
//	@DisplayName("상담 신청 상세 조회용 컨트롤러 테스트")
//	public void testSelectConsultantDetail() throws Exception {
//		
//		// given
//		int conNo = 22;
//		
//		// when & then
//		mockMvc.perform(MockMvcRequestBuilders.get("/student/consultant/detail/" + conNo))
//				.andExpect(MockMvcResultMatchers.status().isOk())
//				.andExpect(MockMvcResultMatchers.forwardedUrl("student/consultant/detail"))
//				.andDo(MockMvcResultHandlers.print());
//	}
	
	// success
	@Test
	@Disabled
	@DisplayName("상담 신청 상세 조회용 컨트롤러 테스트")
	public void testSelectConsultantDetail() throws Exception {
		
		// given
		int no = 22;
		
		// when & then
		mockMvc.perform(MockMvcRequestBuilders.get("/student/consultant/detail/?no=" + no))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.forwardedUrl("student/consultant/detail"))
				.andDo(MockMvcResultHandlers.print());
	}
	
	// success
	@Test
	@Disabled
	@DisplayName("상담 신청 수정용 컨트롤러 테스트")
	public void testUpdateConsultant() throws Exception {
		
		// given
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("conHDate", "2022-05-10");
		params.add("conHTime", "09:00 ~ 09:50");
		params.add("conHTitle", "상담 신청 테스트 제목 수정입니다.");
		params.add("conHContent", "상담 신청 테스트 내용 수정합니다.");
		
		// url에 상담 신청 게시글 번호를 같이 넘기므로 DB에 존재하는 변수 하나를 선언해 준다.
		int no = 22;
		
		// when & then
		mockMvc.perform(MockMvcRequestBuilders.post("/student/consultant/updateForm/?no=" + no).params(params))
				.andExpect(MockMvcResultMatchers.status().is3xxRedirection())
				.andExpect(MockMvcResultMatchers.flash().attributeCount(1))
				.andExpect(MockMvcResultMatchers.redirectedUrl("/student/consultant/list"))
				.andDo(MockMvcResultHandlers.print());
	}
	
	// success
	@Test
	@Disabled
	@DisplayName("상담 신청 삭제용 컨트롤러 테스트")
	public void testDeleteConsultant() throws Exception {
		
		// given
		int no = 40;
		
		// when & then
		mockMvc.perform(MockMvcRequestBuilders.post("/student/consultant/delete/?no=" + no))
				.andExpect(MockMvcResultMatchers.status().is3xxRedirection())
				.andExpect(MockMvcResultMatchers.flash().attributeCount(1))
				.andExpect(MockMvcResultMatchers.redirectedUrl("/student/consultant/list"))
				.andDo(MockMvcResultHandlers.print());
	}
	
}
