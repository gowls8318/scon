package com.scon.project.admin.taskBoard.model.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.scon.project.admin.taskBoard.model.dto.TaskBoardDTO;
import com.scon.project.config.SconApplication;

@SpringBootTest
@ContextConfiguration(classes = {SconApplication.class})
public class TaskBoardMapperTests {
	
	@Autowired
	TaskBoardMapper taskBoardMapper;
	
	@Test
	public void 매퍼_의존성_주입_테스트() {
		
		assertNotNull(taskBoardMapper);
	}
	
	/* 과제 게시판 전체 조회 (O) */
	@Test
	public void 과제_게시판_전체_조회_테스트() {
		//given
//		int clsId = 1;
		
		//when
		List<TaskBoardDTO> taskList = taskBoardMapper.findAllTask(); //clsId
		
		//then
		assertNotNull(taskList);
	}
	
	/* 과제 게시판 글쓰기 전 조회 테스트 */
/*	@Test
	public void 과제_게시판_게시글_작성_전_작성자_조회_테스트() {
		
		//given
		int clsId = 1;
		
		//when
		TaskBoardDTO taskBoard = taskBoardMapper.findMemberName(clsId);
		
		//then
		assertNotNull(taskBoardMapper);
	} */
	 
	/* 과제 게시판 글쓰기 테스트 */
//	@Test
//	public void 과제_게시판_게시글_작성_테스트() {
//		
//		//given
//		TaskBoardDTO taskBoard = new TaskBoardDTO();
//		//taskBoard.setTaskId(null); 시퀀스
//		taskBoard.setMemberId("user01");
//		taskBoard.setClsId(1);
//		taskBoard.setTaskTitle("과제 게시판 게시글 입력 테스트1");
//		taskBoard.setTaskContent("잘 입력이 됐으면 좋겠다 제발제발제발 ㅠㅠㅠㅠㅠㅠ");
//		//taskBoard.setTaskDate(null); SYSDATE
//		
//		//when
//		int result = taskBoardMapper.insertTask(taskBoard);
//		
//		//then
//		assertEquals(1, result);
//	}
//	
	
}
