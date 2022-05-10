package com.scon.project.admin.taskBoard.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.scon.project.admin.taskBoard.model.dto.TaskBoardDTO;


@Mapper
public interface TaskBoardMapper {

	//과제 게시판 전체 조회
	List<TaskBoardDTO> findAllTask(int clsId);

	//과제 게시판 게시글 입력
	int insertTask(TaskBoardDTO taskBoard);

	//
	TaskBoardDTO findMemberName(int clsId);

}
