package com.scon.project.admin.student.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParentsDTO {

//	MEMBER_ID	VARCHAR2(100 BYTE)	No		1	회원아이디
//	PARENTS_TYPE	VARCHAR2(100 BYTE)	Yes		2	가족관계
//	PARENTS_NAME	VARCHAR2(100 BYTE)	No		3	학부모명
//	PHONE	VARCHAR2(30 BYTE)	Yes		4	연락처
	
	private String id;
	private String parentsType;
	private String parentsName;
	private String phone;
	
	
}
