package com.scon.project.admin.student.model.dto;

import com.scon.project.member.model.dto.MemberDTO;
import com.scon.project.member.model.dto.ProfileDTO;

import lombok.Data;

@Data
public class StudentDTO {
	/*
	MEMBER_ID	VARCHAR2(30 BYTE)	No		1	회원아이디
	STUDENT_TYPE	VARCHAR2(100 BYTE)	Yes		2	학생유형
	SCHOOL_NAME	VARCHAR2(100 BYTE)	Yes		3	학교
	SCHOOL_GRADE	NUMBER	Yes		4	학년
	SCHOOL_CLASS	VARCHAR2(100 BYTE)	Yes		5	학급(반)
	CONSULT	VARCHAR2(1000 BYTE)	Yes		6	상담내용
	 */
	
	private String id;					//회원 Id
	private String studentType;			//학생유형
	private String schoolName;			//학교	
	private String schoolGrade;			//학년
	private String schoolClass;			//학급(반)
	private String consult;				//상담내용

	private MemberDTO member;			//회원 기본정보
	private ProfileDTO profile; 		//프로필 사진
	private ParentsDTO parents;			//학부모 정보 
	
}
