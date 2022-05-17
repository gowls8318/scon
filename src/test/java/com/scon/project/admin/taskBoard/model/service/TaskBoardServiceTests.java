package com.scon.project.admin.taskBoard.model.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.scon.project.admin.taskBoard.model.dto.TaskBoardDTO;
import com.scon.project.config.SconApplication;

@SpringBootTest
@ContextConfiguration(classes = {SconApplication.class})
public class TaskBoardServiceTests {

	//의존성 주입
	@Autowired
	private TaskBoardService taskBoardService;
	
	
	//의존성 주입 테스트
	@Test
	public void 서비스_의존성_주입_테스트() {
		assertNotNull(taskBoardService);
	}
	
	//과제 게시판 전체 조회 (O)
//	@Test
//	@Disabled
//	public void 과제_게시판_전체_조회_테스트() {
//		//given
//		int clsId = 1; //수업별 게시판으로 들어가기 위해 clsId 받아오기
//		
//		//when
//		List<TaskBoardDTO> taskList = taskBoardService.findAllTask(clsId); //clsId
//		
//		//then
//		assertNotNull(taskList);
//		
//	}
}
