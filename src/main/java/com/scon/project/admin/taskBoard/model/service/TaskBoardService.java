package com.scon.project.admin.taskBoard.model.service;

import java.util.List;
import java.util.Map;

import com.scon.project.admin.taskBoard.model.dto.TaskBoardDTO;
import com.scon.project.member.model.dto.ProfileDTO;

public interface TaskBoardService {

	//게시판 전체 목록 조회
//	List<TaskBoardDTO> findAllTask(int clsId);
	List<TaskBoardDTO> findAllTask();

	//게시판 입력
	int insertTask(TaskBoardDTO task) throws Exception;

	//게시판 게시글 상세 조회
	List<TaskBoardDTO> findDetail(String taskId);


}
