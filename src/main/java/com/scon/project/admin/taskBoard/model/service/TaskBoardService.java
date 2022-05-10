package com.scon.project.admin.taskBoard.model.service;

import java.util.List;

import com.scon.project.admin.taskBoard.model.dto.TaskBoardDTO;

public interface TaskBoardService {

	//게시판 전체 목록 조회
	List<TaskBoardDTO> findAllTask(int clsId);

}
