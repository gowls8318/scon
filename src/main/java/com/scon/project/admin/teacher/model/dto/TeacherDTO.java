package com.scon.project.admin.teacher.model.dto;

import com.scon.project.member.model.dto.MemberDTO;
import com.scon.project.member.model.dto.ProfileDTO;

import lombok.Data;

@Data
public class TeacherDTO {

 /* MEMBER_ID	VARCHAR2(30 BYTE)	No		1	회원아이디
	JOB_CODE	NUMBER	No		2	직급코드
	RESUME	VARCHAR2(4000 BYTE)	Yes		3	이력사항 */
	
	private String id;
	private int jobCode;
	private String jobName;
	private String resume;
	
	private MemberDTO member;			//회원 기본정보
	
}
