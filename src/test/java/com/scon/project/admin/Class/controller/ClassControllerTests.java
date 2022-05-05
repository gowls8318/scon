package com.scon.project.admin.Class.controller;

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
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.scon.project.admin.Class.dto.ClassDTO;
import com.scon.project.config.SconApplication;
import com.scon.project.member.controller.MemberController;
import com.scon.project.member.model.dto.MemberDTO;

@SpringBootTest
@ContextConfiguration(classes = { SconApplication.class })
public class ClassControllerTests {

	@Autowired
	private ClassController classController;
	private MockMvc mockMvc; // spring web 동작 재현

	@Test
	public void testClassInit() {

		assertNotNull(classController);
		assertNotNull(mockMvc);
	}

	@BeforeEach
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(classController).build();
	}
	
	//강의리스트조회테스트
	@Test
	public void 강의_리스트_조회_컨트롤러_테스트() throws Exception {
		
		//given
		
		//when&then
		mockMvc.perform(MockMvcRequestBuilders.get("/admin/classList"))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.forwardedUrl("admin/class/selectClass"))
		.andDo(MockMvcResultHandlers.print());
		
	}
	
	//강의상세조회테스트
	@Test
	public void 강의상세보기_조회용_서비스_컨트롤러_테스트() throws Exception {
		
		//given
		
		//when&then
		mockMvc.perform(MockMvcRequestBuilders.post("/admin/classList"))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.forwardedUrl("admin/class/selectClass"))
		.andDo(MockMvcResultHandlers.print());
		
	}

	
	// 강의등록테스트
	@Test
	public void 신규_강의_등록_컨트롤러_테스트() throws Exception {
		
		

		// given
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("member.id", "director");
		params.add("clsName", "강의 등록 테스트");
		params.add("clsSubject", "스프링");
		params.add("clsStuNum", "30명");
		params.add("clsGrade", "성인");
		params.add("clsPay", "30000");
		params.add("clsRoom", "L강의실");
		params.add("clsStart", "2020.05.01");
		params.add("clsEnd", "2020.05.30");
		params.add("clsNote", "강의 등록 비고란 테스트");
		params.add("clsStatus", "Y");
		params.add("dayList[0].clsDayId", "2");
		params.add("dayList[1].clsDayId", "3");
		params.add("time[0].clsTimeId", "3");
		params.add("time[1].clsTimeId", "4");

		// when and then
		mockMvc.perform(MockMvcRequestBuilders.post("/admin/classRegist").params(params))
				.andExpect(MockMvcResultMatchers.status().is3xxRedirection())
				// .andExpect(MockMvcResultMatchers.flash().attributeCount(1)) //메세지확인(1)
				.andExpect(MockMvcResultMatchers.redirectedUrl("/admin/classList")) // 메소드 수행 확인
				.andDo(MockMvcResultHandlers.print());

	}

}
