package com.scon.project.admin.taskBoard.model.dto;

import com.scon.project.admin.Class.dto.ClassDTO;

public class TaskBoardDTO {

	private String taskId;
//	private MemberDTO memberId;              //회원 아이디
	private ClassDTO clsId;
	private String taskTitle;                //글제목
	private String taskContent;              //글내용
	private java.sql.Date taskDate;          //작성일시
	private java.sql.Date taskUpdate;        //수정일시
	private char taskDelete;                 //삭제여부
	private int taskView;                    //조회수
}
