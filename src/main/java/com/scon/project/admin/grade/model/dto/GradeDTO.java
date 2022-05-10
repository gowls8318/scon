package com.scon.project.admin.grade.model.dto;



import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class GradeDTO {
	
	/*
	GRADE_ID	VARCHAR2(50 BYTE)	No		1	성적번호
	MEMBER_ID	VARCHAR2(30 BYTE)	No		2	회원아이디
	CLS_ID	NUMBER	No		3	강의번호
	GRADE_DATE	DATE	No	"SYSDATE	"	4	등록일    --> 데이터타입 VARCHAR2(30)으로 변경
	GRADE	NUMBER	No		5	점수
	*/

	private int gradeId;
	private String memberId;
	private int clsId;
	private String gradeDate;
	private int grade;
	// 회원이름
	private String memberName;
	// 수업이름
	private String clsName;
	


	
}
