package com.scon.project.student.taskBoard.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.scon.project.admin.taskBoard.model.dto.FileDTO;
import com.scon.project.admin.taskBoard.model.dto.TaskBoardDTO;
import com.scon.project.common.paging.Criteria;

@Mapper
public interface StudentTaskBoardMapper {

	int total(TaskBoardDTO taskBoard);

	//전체 게시글 조회
	List<TaskBoardDTO> findAllStudentTask(TaskBoardDTO taskBoard, Criteria cri);

	//게시글 상세 조회
	List<TaskBoardDTO> findStudentTaskDetail(String taskId);
	
	//파일 상세 조회
	List<FileDTO> findTaskFiles(String taskId);
	//조회수 증가
	boolean updateView(String taskId);

	

}
