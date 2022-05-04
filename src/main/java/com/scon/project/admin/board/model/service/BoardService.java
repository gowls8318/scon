package com.scon.project.admin.board.model.service;

import java.util.List;

import com.scon.project.admin.board.model.dto.BoardDTO;

public interface BoardService {

	List<BoardDTO> findAllBoardList();

}
