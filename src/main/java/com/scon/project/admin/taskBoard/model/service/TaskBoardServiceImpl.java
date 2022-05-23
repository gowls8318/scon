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
	
	//게시판 전체 목록 조회 + 페이징 (O)
	@Override
	public List<TaskBoardDTO> findAllTask(TaskBoardDTO taskBoard, Criteria cri) {
		return taskBoardMapper.findAllTask(taskBoard, cri); 
		
	}
	
	//글 전체 갯수 (O)
	@Override
	public int total(TaskBoardDTO taskBoard) {
		
		int result = taskBoardMapper.total(taskBoard);
		
		return result;
	}

	//게시판 글 입력 (O)
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

	//게시판 게시글 상세 조회 (O)
	@Override
	public TaskBoardDTO findDetail(String taskId) {
		return taskBoardMapper.findDetail(taskId);
	}

	//게시판 게시글 파일 상세 조회 (O) 
	@Override
	public List<FileDTO> findFiles(String taskId) {
		return taskBoardMapper.findFiles(taskId);
	}

	//게시판 게시글 삭제 (O)
	@Override
	public boolean deleteBoard(String taskId) throws Exception {
		
		int result = taskBoardMapper.deleteBoard(taskId);
		
		if(result <= 0) {
			throw new Exception("게시글 삭제 실패");
		}
		
		return result > 0 ? true : false;
	}

	//게시글 수정 전 게시글 상세 조회
	@Override
	public List<TaskBoardDTO> findModifyTask(String taskId) {
		return taskBoardMapper.findModifyTask(taskId);
	}

	//게시글 수정 전 파일 상세 조회
	@Override
	public List<FileDTO> findModifyfiles(String taskId) {
		return taskBoardMapper.findModifyfiles(taskId);
	}

	//게시글 수정 (+파일)
		@Override
		public int modifyTaskAndFiles(TaskBoardDTO task) throws Exception {
			
			int result = 0, result1 = 0;
						
			for(FileDTO files : task.getFileList()) {
				 result1 += taskBoardMapper.modifyFiles(files);
			}
			
			if(result1 == task.getFileList().size()) {
				int result2 = taskBoardMapper.modifyTask(task);
				result = (result2 > 0 ) ? 1 : 0;
			}
			
			return result;
		}

	//게시글 수정 (+text)
	@Override
	public int modifyOnlyContent(TaskBoardDTO task) {
		return taskBoardMapper.modifyTask(task);
	}

	//조회수 증가
	@Override
	public boolean updateView(String taskId) {
		return taskBoardMapper.updateView(taskId);
	}

	




	
}
