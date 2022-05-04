package com.scon.project.admin.board.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.scon.project.admin.board.model.dao.BoardMapper;
import com.scon.project.admin.board.model.dto.BoardDTO;


@Service("boardService")
@Transactional
public class BoardServiceImpl implements BoardService {
	
	private final BoardMapper boardMapper;
	
	@Autowired
	public BoardServiceImpl(BoardMapper boardMapper) {
		this.boardMapper = boardMapper;
	}

	@Override
	public List<BoardDTO> findAllBoardList() {
		return boardMapper.findAllBoardList();
	}


}
