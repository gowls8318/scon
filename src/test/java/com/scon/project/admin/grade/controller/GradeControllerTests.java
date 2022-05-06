package com.scon.project.admin.grade.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
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

import com.scon.project.admin.grade.model.dto.GradeDTO;
import com.scon.project.config.SconApplication;

@SpringBootTest
@ContextConfiguration(classes = {SconApplication.class})
public class GradeControllerTests {

	@Autowired
	private GradeController gradeController;
	private MockMvc mockMvc;
	
	/* 의존성 주입 테스트 */
	@Test
	@Disabled
	public void testInit() {
		
		assertNotNull(gradeController);
		assertNotNull(mockMvc);
	}
	
	@BeforeEach
	public void SetUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(gradeController).build();
		
	}
	
	/* 성적 조회 테스트 (complete) */
	@Test   
	@Disabled
	public void 전체_성적_조회용_컨트롤러_테스트() throws Exception {
		//given
		
		//when&then
		mockMvc.perform(MockMvcRequestBuilders.get("/admin/gradeList?clsId=1"))
			   .andExpect(MockMvcResultMatchers.status().isOk())
			   .andExpect(MockMvcResultMatchers.forwardedUrl("admin/grade/gradeList"))
			   .andDo(MockMvcResultHandlers.print());
	}
	
	/* 성적 입력 전 학생 조회 테스트 (complete) */
	@Test
	public void 성적_입력_학생_조회_컨트롤러_테스트() throws Exception {
		
		//given
		
		//when & then
		mockMvc.perform(MockMvcRequestBuilders.get("/admin/insertGrade"))
		       .andExpect(MockMvcResultMatchers.status().isOk())
		       .andExpect(MockMvcResultMatchers.forwardedUrl("admin/grade/insertGrade"))
		       .andDo(MockMvcResultHandlers.print());
	}
	

	
	/* 성적 입력 테스트 */
	@Test
	public void 성적_입력_컨트롤러_테스트() throws Exception {
		
		//given
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("gradeId", "1");
		params.add("memberId", "user02");
		params.add("clsId", "1");
		params.add("gradeDate","2022-05-04");
		params.add("grade", "90");
	
		//when & then
		mockMvc.perform(MockMvcRequestBuilders.post("/admin/gradeList").params(params))
		       .andExpect(MockMvcResultMatchers.status().is3xxRedirection()) //redirect 확인
		       .andExpect(MockMvcResultMatchers.flash().attributeCount(1))  //RedirectAttributes에 담은 1개의 메세지 확인
		       .andExpect(MockMvcResultMatchers.redirectedUrl("/admin/gradeList"))
		       .andDo(MockMvcResultHandlers.print());

	}
	
	
	
	
	
}
