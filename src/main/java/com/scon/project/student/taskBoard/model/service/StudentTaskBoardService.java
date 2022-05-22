package com.scon.project.student.taskBoard.model.service;

import java.util.List;

import com.scon.project.admin.taskBoard.model.dto.FileDTO;
import com.scon.project.admin.taskBoard.model.dto.TaskBoardDTO;
import com.scon.project.common.paging.Criteria;


public interface StudentTaskBoardService {

	int total(TaskBoardDTO taskBoard);

	//게시글 전체 조회
	List<TaskBoardDTO> findAllStudentTask(TaskBoardDTO taskBoard, Criteria cri);

	//게시글 상세 조회
	List<TaskBoardDTO> findStudentTaskDetail(String taskId);

	//게시글 상세 조회 (파일)
	List<FileDTO> findTaskFiles(String taskId);

	//조회수 증가
	boolean updateView(String taskId);

	

	
	

	

	

	

	

	



}
