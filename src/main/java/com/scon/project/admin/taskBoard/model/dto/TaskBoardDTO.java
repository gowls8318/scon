package com.scon.project.admin.taskBoard.model.dto;

import lombok.Data;

@Data
public class TaskBoardDTO {

	private String taskId;
	private String memberId;              //회원 아이디   (Member)
	private	int clsId;                 //클래스 아이디 (Class)
	private String taskTitle;                //글제목
	private String taskContent;              //글내용
	private java.sql.Date taskDate;          //작성일시
	private java.sql.Date taskUpdate;        //수정일시
	private char taskDelete;                 //삭제여부
	private int taskView;                    //조회수
	
	
	private int fileId; //파일 아이디 (TaskFile DTO)
	private String memberName;
}
