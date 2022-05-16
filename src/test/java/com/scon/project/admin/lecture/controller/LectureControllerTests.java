package com.scon.project.admin.lecture.controller;

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
public class LectureControllerTests {

	@Autowired
	private LectureController lectureController;
	private MockMvc mockMvc;
	
	// success
	@Test
	@Disabled
	@DisplayName("의존성 주입 테스트")
	public void testInit() {
		
		assertNotNull(lectureController);	// success
		assertNotNull(mockMvc);				// success
	}
	
	@BeforeEach
	public void setUp() {
		
		mockMvc = MockMvcBuilders.standaloneSetup(lectureController).build();
	}
	
	// success
	@Test
	@Disabled
	@DisplayName("수강 내역 조회용 컨트롤러 테스트")
	public void testSelectLecture() throws Exception {
		
		// when & then
		mockMvc.perform(MockMvcRequestBuilders.get("/admin/lecture/list"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.forwardedUrl("admin/lecture/list"))
				.andDo(MockMvcResultHandlers.print());	
	}
	
	// success
	@Test
	@Disabled
	@DisplayName("강의 조회용 컨트롤러 테스트")
	public void testSelectClass() throws Exception {
		
		// when & then
		mockMvc.perform(MockMvcRequestBuilders.get("/admin/lecture/insertForm"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.forwardedUrl("admin/lecture/insertForm"))
				.andDo(MockMvcResultHandlers.print());	
	}
	
	// success
	@Test
	@Disabled
	@DisplayName("수강 등록용 컨트롤러 테스트")
	public void testInsertLecture() throws Exception {
		
		// given
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("memberId", "user02");
		params.add("clsId", "22");
		params.add("lecPay", "100000");
		
		// when & then
		mockMvc.perform(MockMvcRequestBuilders.post("/admin/lecture/insertForm").params(params))
				.andExpect(MockMvcResultMatchers.status().is3xxRedirection())
				.andExpect(MockMvcResultMatchers.flash().attributeCount(1))
				.andExpect(MockMvcResultMatchers.redirectedUrl("/admin/lecture/list"))
				.andDo(MockMvcResultHandlers.print());
	}
	
	// success
	@Test
	@Disabled
	@DisplayName("수강 정보 조회용 컨트롤러 테스트")
	public void testSelectConsultantDetail() throws Exception {
		
		// given
		int no = 46;
		
		// when & then
		mockMvc.perform(MockMvcRequestBuilders.get("/admin/lecture/updateForm/?no=" + no))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.forwardedUrl("admin/lecture/updateForm"))
				.andDo(MockMvcResultHandlers.print());
	}
	
	// success
	@Test
	@Disabled
	@DisplayName("수강 수정용 컨트롤러 테스트")
	public void testUpdateConsultant() throws Exception {
		
		// given
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("lecStatus", "수강 완료");
		params.add("accStatus", "수납");
		params.add("accDate", "2022-05-12");
		params.add("accOption", "현금");
		
		int no = 14;
		
		// when & then
		mockMvc.perform(MockMvcRequestBuilders.post("/admin/lecture/updateForm/?no=" + no).params(params))
				.andExpect(MockMvcResultMatchers.status().is3xxRedirection())
				.andExpect(MockMvcResultMatchers.flash().attributeCount(1))
				.andExpect(MockMvcResultMatchers.redirectedUrl("/admin/lecture/list"))
				.andDo(MockMvcResultHandlers.print());
	}
	
	// success
	@Test
	@Disabled
	@DisplayName("수강 삭제용 컨트롤러 테스트")
	public void testDeleteLecture() throws Exception {
		
		// given
		int no = 11;
		
		// when & then
		mockMvc.perform(MockMvcRequestBuilders.post("/admin/lecture/delete/?no=" + no))
				.andExpect(MockMvcResultMatchers.status().is3xxRedirection())
				.andExpect(MockMvcResultMatchers.flash().attributeCount(1))
				.andExpect(MockMvcResultMatchers.redirectedUrl("/admin/lecture/list"))
				.andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	@DisplayName("환불 등록용 컨트롤러 테스트")
	public void testInsertRefund() throws Exception {
		
		// given
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("lecNo", "50");
		params.add("refPay", "100000");
		params.add("refDate", "2022-05-16");
		
		// when & then
		mockMvc.perform(MockMvcRequestBuilders.post("/admin/lecture/registRefund").params(params))
				.andExpect(MockMvcResultMatchers.status().is3xxRedirection())
				.andExpect(MockMvcResultMatchers.flash().attributeCount(1))
				.andExpect(MockMvcResultMatchers.redirectedUrl("/admin/lecture/list"))
				.andDo(MockMvcResultHandlers.print());
	}
	
}
