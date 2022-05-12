package com.scon.project.admin.taskBoard.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.scon.project.admin.grade.model.dto.GradeDTO;
import com.scon.project.admin.taskBoard.model.dao.TaskBoardMapper;
import com.scon.project.admin.taskBoard.model.dto.FileDTO;
import com.scon.project.admin.taskBoard.model.dto.TaskBoardDTO;
import com.scon.project.member.model.dto.ProfileDTO;

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
	
	//게시판 글 입력
//	@Override
//	public int insertTask(TaskBoardDTO task) throws Exception {
//		
//		int result = 0;
//		
//		int result1 = taskBoardMapper.insertTask(task);
//		
//		int result2 = 0;
//		
//		for(FileDTO files : task.getFileList()) {
//			result2 += taskBoardMapper.insertFile(files);
//		}
//		
//		if(result1 > 0 && result2 > task.getFileList().size()) {
//			result = 1;
//		} else {
//			throw new Exception("게시글 등록 실패");
//		}
//		
//		return result;
//	}

	
}
