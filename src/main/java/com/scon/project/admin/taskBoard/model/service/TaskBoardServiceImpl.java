package com.scon.project.admin.taskBoard.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.scon.project.admin.taskBoard.model.dao.TaskBoardMapper;
import com.scon.project.admin.taskBoard.model.dto.TaskBoardDTO;

@Service("taskBoardService")
@Transactional
public class TaskBoardServiceImpl implements TaskBoardService {
	
	private final TaskBoardMapper taskBoardMapper;
	
	@Autowired
	public TaskBoardServiceImpl(TaskBoardMapper taskBoardMapper) {
		this.taskBoardMapper = taskBoardMapper;
	}
	
	//게시판 전체 목록 조회
	@Override
	public List<TaskBoardDTO> findAllTask(int clsId) {
		return taskBoardMapper.findAllTask(clsId);
	}
	
	
	
}
