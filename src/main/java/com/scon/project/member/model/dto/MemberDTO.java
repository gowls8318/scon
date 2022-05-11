package com.scon.project.member.model.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
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
	
	private String id;								//회원아이디
	private String name;							//회원명
	private String password;						//비밀번호
	private String tempPwdYN;
	private String phone;							//연락처
	private String email;							//이메일
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd") 
	private java.sql.Date birthDay;					//생년월일
	private String address;							//주소
	private String gender;							//성별
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd") 
	private java.sql.Date enrollDate;				//등록일
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd") 
	private java.sql.Date delDate;					//삭제일
	private String status;							//회원상태

	/* TB_MEMBER_ROLE - 한 멤버는 여러 권한을 가질 수 있다 */
	private List<MemberRoleDTO> memberRoleList;		//권한 목록
	
}
