package com.scon.project.admin.taskBoard.model.service;

import java.util.List;
import java.util.Map;

import com.scon.project.admin.taskBoard.model.dto.FileDTO;
import com.scon.project.admin.taskBoard.model.dto.TaskBoardDTO;
import com.scon.project.common.paging.Criteria;
import com.scon.project.member.model.dto.ProfileDTO;

public interface TaskBoardService {

	//게시판 전체 목록 조회
	List<TaskBoardDTO> findAllTask(TaskBoardDTO taskBoard, Criteria cri);
	
	//게시판 전체 글 갯수
	int total(TaskBoardDTO taskBoard);

	//게시판 입력
	int insertTask(TaskBoardDTO task) throws Exception;

	//게시판 게시글 상세 조회
	TaskBoardDTO findDetail(String taskId);

	//게시판 게시글 상세 조회 (파일)
	List<FileDTO> findFiles(String taskId);

	//게시판 게시글 삭제
	boolean deleteBoard(String taskId) throws Exception;

	//게시판 수정 전 내용 조회
	List<TaskBoardDTO> findModifyTask(String taskId);
	//게시판 수정 전 파일 조회
	List<FileDTO> findModifyfiles(String taskId);

	//게시판 수정 (+파일)
	int modifyTaskAndFiles(TaskBoardDTO task) throws Exception;

	//게시판 수정 (컨텐츠)
	int modifyOnlyContent(TaskBoardDTO task);
	
	//조회수
//	int updateView(int taskView);
	boolean updateView(String taskId);



}
