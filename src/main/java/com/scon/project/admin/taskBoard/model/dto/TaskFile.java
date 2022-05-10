package com.scon.project.admin.taskBoard.model.dto;

import lombok.Data;

@Data
public class TaskFile {

	/*
	 * TASK_ID	VARCHAR2(50 BYTE)	No		1	과제번호
	   FILE_ID	NUMBER	No		2	첨부파일번호
	 */

	private String taskId;
	private int fileId;
	
}
