package com.scon.project.student.taskBoard.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.scon.project.admin.taskBoard.model.dto.TaskBoardDTO;
import com.scon.project.common.paging.Criteria;
import com.scon.project.student.taskBoard.model.dao.StudentTaskBoardMapper;

@Service("studentTaskBoardService")
@Transactional
public class StudentTaskBoardServiceImpl implements StudentTaskBoardService {

	private StudentTaskBoardMapper studentTaskBoardMapper;
	
	@Autowired
	public StudentTaskBoardServiceImpl(StudentTaskBoardMapper studentTaskBoardMapper) {
		this.studentTaskBoardMapper = studentTaskBoardMapper;
	}
	
	
//	@Override
//	public int total(TaskBoardDTO taskBoard) {
//		int result = studentTaskBoardMapper.total(taskBoard);
//		return result;
//	}

	//게시판 전체 조회 + 페이징
//	@Override
//	public List<TaskBoardDTO> findAllStudentTask(TaskBoardDTO taskBoard, Criteria cri) {
//		return studentTaskBoardMapper.findAllStudentTask(taskBoard, cri);
//	}

	
}
