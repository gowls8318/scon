package com.scon.project.admin.board.model.dto;

import java.sql.Date;

import lombok.Data;

@Data	
public class BoardDTO {
	
	private int brdNo;
	private String headId;
	private String memberId;
	private int clsId;
	private String brdTitle;
	private String brdContent;
	private Date brdDate;
	private Date brdUpdate;
	private int brdView;
	private String brdDelete;
}
