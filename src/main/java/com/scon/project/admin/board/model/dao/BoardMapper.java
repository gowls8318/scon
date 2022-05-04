package com.scon.project.admin.board.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.scon.project.admin.board.model.dto.BoardDTO;

@Mapper
public interface BoardMapper {

	List<BoardDTO> findAllBoardList();

}
