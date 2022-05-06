package com.scon.project.admin.consultant.controller;

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

import com.scon.project.admin.consultant.model.dto.ConsultantDTO;
import com.scon.project.config.SconApplication;

@SpringBootTest
@ContextConfiguration(classes = {SconApplication.class})
public class ConsultantControllerTests {

	@Autowired
	private ConsultantController consultantController;
	private MockMvc mockMvc;
	
	// success
	@Test
	@Disabled
	@DisplayName("의존성 주입 테스트")
	public void testInit() {
		
		assertNotNull(consultantController);	// success
		assertNotNull(mockMvc);					// success
	}
	
	@BeforeEach
	public void setUp() {
		
		mockMvc = MockMvcBuilders.standaloneSetup(consultantController).build();
	}
	
	// success
	@Test
	@Disabled
	@DisplayName("상담 신청 내역 조회용 컨트롤러 테스트")
	public void testSelectConsultantHope() throws Exception {
		
		// when & then
		mockMvc.perform(MockMvcRequestBuilders.get("/admin/consultant/hopeList"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.forwardedUrl("admin/consultant/hopeList"))
				.andDo(MockMvcResultHandlers.print());
	}
	
	// success
	@Test
	@Disabled
	@DisplayName("상담 신청 상세 조회용 컨트롤러 테스트")
	public void testSelectConsultantHopeDetail() throws Exception {
		
		// given
		int no = 22;
		
		// when & then
		mockMvc.perform(MockMvcRequestBuilders.get("/admin/consultant/hopeDetail/?no=" + no))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.forwardedUrl("admin/consultant/hopeDetail"))
				.andDo(MockMvcResultHandlers.print());
	}
	
	// success
	@Test
	@Disabled
	@DisplayName("상담 일지 내역 조회용 컨트롤러 테스트")
	public void testSelectConsultantList() throws Exception {
		
		// when & then
		mockMvc.perform(MockMvcRequestBuilders.get("/admin/consultant/list"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.forwardedUrl("admin/consultant/list"))
				.andDo(MockMvcResultHandlers.print());
	}
	
	// success
	@Test
	@Disabled
	@DisplayName("상담 일지 등록용 컨트롤러 테스트")
	public void testInsertConsultant() throws Exception {
		
		// given
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("conDate", "2022-05-06");
		params.add("conType", "학업 문제");
		params.add("conWay", "개인 면담");
		params.add("conImp", "★★★★★");
		params.add("conContent", "개인 면담 진행");
		
		int no = 56;
		
		// when & then
		mockMvc.perform(MockMvcRequestBuilders.post("/admin/consultant/insertForm/?no=" + no).params(params))
				.andExpect(MockMvcResultMatchers.status().is3xxRedirection())
				.andExpect(MockMvcResultMatchers.flash().attributeCount(1))
				.andExpect(MockMvcResultMatchers.redirectedUrl("/admin/consultant/hopeList"))
				.andDo(MockMvcResultHandlers.print());
	}
	
	// success
	@Test
	@Disabled
	@DisplayName("상담 일지 상세 조회용 컨트롤러 테스트")
	public void testConsultantDetail() throws Exception {
		
		// given
		int no = 52;
		
		// when & then
		mockMvc.perform(MockMvcRequestBuilders.get("/admin/consultant/detail/?no=" + no))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.forwardedUrl("admin/consultant/detail"))
				.andDo(MockMvcResultHandlers.print());
	}
	
	// success
	@Test
	@Disabled
	@DisplayName("상담 일지 수정용 컨트롤러 테스트")
	public void testUpdateConsultant() throws Exception {
		
		// given
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("conType", "진로 문제");
		params.add("conWay", "전화 면담");
		params.add("conImp", "★★★☆☆");
		params.add("conContent", "상담 일지 내용 진로 문제로 수정입니다.");
		
		int no = 52;
		
		// when & then
		mockMvc.perform(MockMvcRequestBuilders.post("/admin/consultant/updateForm/?no=" + no).params(params))
				.andExpect(MockMvcResultMatchers.status().is3xxRedirection())
				.andExpect(MockMvcResultMatchers.flash().attributeCount(1))
				.andExpect(MockMvcResultMatchers.redirectedUrl("/admin/consultant/list"))
				.andDo(MockMvcResultHandlers.print());
	}
	
	// success
	@Test
	@Disabled
	@DisplayName("상담 일지 삭제용 컨트롤러 테스트")
	public void testDeleteConsultant() throws Exception {
		
		// given
		int no = 55;
		
		// when & then
		mockMvc.perform(MockMvcRequestBuilders.post("/admin/consultant/delete/?no=" + no))
				.andExpect(MockMvcResultMatchers.status().is3xxRedirection())
				.andExpect(MockMvcResultMatchers.flash().attributeCount(1))
				.andExpect(MockMvcResultMatchers.redirectedUrl("/admin/consultant/list"))
				.andDo(MockMvcResultHandlers.print());
	}
	
}
