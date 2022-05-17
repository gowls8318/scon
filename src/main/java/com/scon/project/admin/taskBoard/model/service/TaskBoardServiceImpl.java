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
import com.scon.project.common.paging.Criteria;
import com.scon.project.member.model.dto.ProfileDTO;

@Service("taskBoardService")
@Transactional
public class TaskBoardServiceImpl implements TaskBoardService {
	
	private final TaskBoardMapper taskBoardMapper;
	
	@Autowired
	public TaskBoardServiceImpl(TaskBoardMapper taskBoardMapper) {
		this.taskBoardMapper = taskBoardMapper;
	}
	
	//게시판 전체 목록 조회 + 페이징
	@Override
	public List<TaskBoardDTO> findAllTask(TaskBoardDTO taskBoard, Criteria cri) {
		return taskBoardMapper.findAllTask(taskBoard, cri); 
		
	}
	
	//글 전체 갯수
	@Override
	public int total(TaskBoardDTO taskBoard) {
		
		int result = taskBoardMapper.total(taskBoard);
		
		return result;
	}

	
	
	//게시판 전체 목록 조회
//	@Override
//	public List<TaskBoardDTO> findAllTask() {
//		return taskBoardMapper.findAllTask();
//	}
	

	//게시판 글 입력
	@Override
	public int insertTask(TaskBoardDTO task) throws Exception {
		
		int result = 0;
		
		int result1 = taskBoardMapper.insertTask(task);
		
		int result2 = 0;
		
		int result3 = 0;
		
		for(FileDTO files : task.getFileList()) {
			result2 += taskBoardMapper.insertFile(files);
		} 
		
		if(result1 > 0 && result2 == task.getFileList().size()) {
			result3 = taskBoardMapper.insertTaskFileTB(task);
			result = (result3 > 0) ? 1 : 0;
		} else {
			throw new Exception("게시글 등록 실패");
		}

		return result;
	}

	//게시판 게시글 상세 조회 
	@Override
	public List<TaskBoardDTO> findDetail(String taskId) {
		return taskBoardMapper.findDetail(taskId);
	}

	//게시판 게시글 상세 조회 (파일)
	@Override
	public List<FileDTO> findFiles(String taskId) {
		return taskBoardMapper.findFiles(taskId);
	}

	//게시판 게시글 삭제
	@Override
	public boolean deleteBoard(String taskId) throws Exception {
		
		int result = taskBoardMapper.deleteBoard(taskId);
		
		if(result <= 0) {
			throw new Exception("게시글 삭제 실패");
		}
		
		return result > 0 ? true : false;
	}




	
}
