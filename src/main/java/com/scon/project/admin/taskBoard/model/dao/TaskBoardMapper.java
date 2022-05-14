package com.scon.project.admin.taskBoard.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.scon.project.admin.taskBoard.model.dto.FileDTO;
import com.scon.project.admin.taskBoard.model.dto.TaskBoardDTO;
import com.scon.project.member.model.dto.ProfileDTO;


@Mapper
public interface TaskBoardMapper {

	//과제 게시판 전체 조회
//	List<TaskBoardDTO> findAllTask(int clsId);
	List<TaskBoardDTO> findAllTask();

	//과제 게시판 게시글 입력 (글 제목, 내용 insert)
	int insertTask(TaskBoardDTO task) throws Exception;

	//과제 게시판 게시글 입력 (파일 insert)
	int insertFile(FileDTO files) throws Exception;

	//과제 게시판 게시글 입력 (TB_TASK_FILE insert)
	int insertTaskFileTB(TaskBoardDTO task);

	//과제 게시판 게시글 상세 조회
	List<TaskBoardDTO> findDetail(String taskId);

	
	//TaskBoardDTO findMemberName(int clsId);

}
