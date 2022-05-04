package com.scon.project.admin.grade.model.dto;

import com.scon.project.admin.Class.dto.ClassDTO;
import com.scon.project.member.model.dto.AuthorityDTO;
import com.scon.project.member.model.dto.MemberDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
public class GradeDTO {
	
	/*
	GRADE_ID	VARCHAR2(50 BYTE)	No		1	성적번호
	MEMBER_ID	VARCHAR2(30 BYTE)	No		2	회원아이디
	CLS_ID	NUMBER	No		3	강의번호
	GRADE_DATE	DATE	No	"SYSDATE	"	4	등록일
	GRADE	NUMBER	No		5	점수
	*/

	private int gradeId;
	private String memberId;
	private int clsId;
	private java.sql.Date gradeDate;
	private int grade;

	
	


	
}
