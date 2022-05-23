package com.scon.project.admin.taskBoard.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.scon.project.admin.taskBoard.model.dto.FileDTO;
import com.scon.project.admin.taskBoard.model.dto.TaskBoardDTO;
import com.scon.project.common.paging.Criteria;


@Mapper
public interface TaskBoardMapper {

	//과제 게시판 전체 조회 + 페이징
	List<TaskBoardDTO> findAllTask(@Param("taskBoard") TaskBoardDTO taskBoard, @Param("cri") Criteria cri);
//	List<TaskBoardDTO> findAllTask();
	
	//글 전체 갯수
	int total(TaskBoardDTO taskBoard);

	//과제 게시판 게시글 입력 (글 제목, 내용 insert)
	int insertTask(TaskBoardDTO task) throws Exception;

	//과제 게시판 게시글 입력 (파일 insert)
	int insertFile(FileDTO files) throws Exception;

	//과제 게시판 게시글 입력 (TB_TASK_FILE insert)
	int insertTaskFileTB(TaskBoardDTO task);

	//과제 게시판 게시글 상세 조회
	TaskBoardDTO findDetail(String taskId);

	//과제 게시판 게시글 상세 조회 (파일)
	List<FileDTO> findFiles(String taskId);

	//게시판 게시글 삭제
	int deleteBoard(String taskId);

	//게시글 수정 전 상세 조회
	List<TaskBoardDTO> findModifyTask(String taskId);
	
	//게시글 수정 전 파일 상세 조회
	List<FileDTO> findModifyfiles(String taskId);

	/* 게시글 수정 (파일도 함께 업데이트)*/
	//1. 새로운 파일 업데이트
	int modifyFiles(FileDTO files) throws Exception;
	//2. 컨텐츠 업데이트
	int modifyTask(TaskBoardDTO task);

	//조회수
//	int updateView(int taskView);
	boolean updateView(String taskId);

}
