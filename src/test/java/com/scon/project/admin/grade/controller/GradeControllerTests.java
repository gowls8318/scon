package com.scon.project.admin.grade.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.scon.project.config.SconApplication;

@SpringBootTest
@ContextConfiguration(classes = {SconApplication.class})
public class GradeControllerTests {

	@Autowired
	private GradeController gradeController;
	private MockMvc mockMvc;
	
	/* 의존성 주입 테스트 */
	@Test
	public void testInit() {
		
		assertNotNull(gradeController);
		assertNotNull(mockMvc);
	}
	
	@BeforeEach
	public void SetUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(gradeController).build();
		
	}
	
	@Test
	public void 전체_성적_조회용_컨트롤러_테스트() throws Exception {
		//given
		
		//when&then
		mockMvc.perform(MockMvcRequestBuilders.get("/admin/gradeList"))
			   .andExpect(MockMvcResultMatchers.status().isOk())
			   .andExpect(MockMvcResultMatchers.forwardedUrl("admin/grade/gradeList"))
			   .andDo(MockMvcResultHandlers.print());
	}
	
	
	
	
	
	
	
	
	
}
