package com.scon.project.admin.taskBoard.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.scon.project.config.SconApplication;

@SpringBootTest
@ContextConfiguration(classes = {SconApplication.class})
public class TaskBoardControllerTests {

	@Autowired
	private TaskBoardController taskBoardController;
	private MockMvc mockMvc;
	
	//의존성 주입 테스트
	@Test
	public void testInit () {
		assertNotNull(taskBoardController);
	}

//	@Test
//	@Disabled
//	public void 과제_게시판_전체_조회_테스트() throws Exception {
//		mockMvc.perform(MockMvcRequestBuilders.get("/admin/taskBoardList"))
//			   .andExpect(MockMvcResultMatchers.status().isOk())
//			   .andExpect(MockMvcResultMatchers.forwardedUrl("admin/taskBoard/taskBoardList"))
//			   .andDo(MockMvcResultHandlers.print());
//	}
//
	
}

