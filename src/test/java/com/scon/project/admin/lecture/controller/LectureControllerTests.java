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
		mockMvc.perform(MockMvcRequestBuilders.get("/admin/student/studentList"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.forwardedUrl("admin/student/studentList"))
				.andDo(MockMvcResultHandlers.print());
	}
	
	// success
	@Test
	@Disabled
	@DisplayName("전체 강의 조회용 컨트롤러 테스트")
	public void testFindClass() throws Exception {
		
		// when & then
		mockMvc.perform(MockMvcRequestBuilders.get("/admin/student/lectureRegist"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType("application/json; charset=UTF-8"))
				.andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	@DisplayName("강의 정보 조회용 컨트롤러 테스트")
	public void testFindClassDetail() throws Exception {
		
		// when & then
		mockMvc.perform(MockMvcRequestBuilders.get("/admin/student/lectureRegist"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.forwardedUrl("admin/student/lectureRegist"))
				.andDo(MockMvcResultHandlers.print());
	}
	
//	@Test
//	@Disabled
//	@DisplayName("수강 상세 조회용 컨트롤러 테스트")
//	public void testSelectLectureDetail() throws Exception {
//		
//		// given
//		int no = 12;
//		
//		// when & then
//		mockMvc.perform(MockMvcRequestBuilders.get("/admin/student/lectureModify/?no=" + no))
//				.andExpect(MockMvcResultMatchers.status().isOk())
//				.andExpect(MockMvcResultMatchers.forwardedUrl("admin/student/lectureModify"))
//				.andDo(MockMvcResultHandlers.print());
//	}
	
	// success
//	@Test
//	
//	@DisplayName("수강 등록용 컨트롤러 테스트")
//	public void testInsertLecture() throws Exception {
//		
//		// given
//		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
//		params.add("clsId", "22");
//		params.add("lecPay", "300000");
//		
//		// when & then
//		mockMvc.perform(MockMvcRequestBuilders.post("/admin/student/subMenu3").params(params))
//				.andExpect(MockMvcResultMatchers.status().is3xxRedirection())
//				.andExpect(MockMvcResultMatchers.flash().attributeCount(1))
//				.andExpect(MockMvcResultMatchers.redirectedUrl("/admin/student/subMenu2"))
//				.andDo(MockMvcResultHandlers.print());
//	}
	
}
