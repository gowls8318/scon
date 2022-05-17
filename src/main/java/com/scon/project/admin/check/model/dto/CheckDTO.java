package com.scon.project.admin.check.model.dto;


import com.scon.project.admin.Class.dto.ClassDTO;
import com.scon.project.admin.lecture.model.dto.LectureDTO;
import com.scon.project.member.model.dto.MemberDTO;

import lombok.Data;

@Data
public class CheckDTO {
	
	private int no;
	private int lecNo;
	private LectureDTO lec;
	private MemberDTO member;
	private ClassDTO cls;
	private String chkStart;
	private String chkFinish;
	private java.sql.Date chkDate;
	

}
