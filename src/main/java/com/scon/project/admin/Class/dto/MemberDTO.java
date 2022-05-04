package com.scon.project.admin.Class.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class MemberDTO {

	
	/*
	MEMBER_ID	VARCHAR2(30 BYTE)	No		1	회원아이디
	MEMBER_NAME	VARCHAR2(100 BYTE)	No		2	회원명
	MEMBER_PWD	VARCHAR2(100 BYTE)	No		3	회원비밀번호
	TEMP_PWD_YN	CHAR(1 BYTE)	Yes	"'N'	"	4	임시비밀번호여부
	PHONE	VARCHAR2(30 BYTE)	Yes		5	연락처
	EMAIL	VARCHAR2(100 BYTE)	Yes		6	이메일
	BIRTHDAY	DATE	Yes		7	생년월일
	ADDRESS	VARCHAR2(1000 BYTE)	Yes		8	주소
	GENDER	CHAR(1 BYTE)	Yes		9	성별
	ENROLL_DATE	DATE	Yes	"SYSDATE	"	10	등록일자
	DEL_DATE	DATE	Yes		11	삭제일자
	MEMBER_ST	VARCHAR2(100 BYTE)	Yes		12	상태
	 */
	
	
	private String memberId; 
	private String memberName;
	private String memberPwd;
	private String tempPwdYn;
	private String phone;
	private int email;
	private Date birthday;
	private String address;
	private String gender;
	private Date enrollDate;
	private Date delDate;
	private String memberSt;
	
}
