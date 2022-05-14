package com.scon.project.admin.check.model.dto;

import java.util.Date;

import com.scon.project.admin.Class.dto.ClassDTO;
import com.scon.project.admin.Class.dto.MemberDTO;

import lombok.Data;

@Data
public class CheckDTO {
	
	private int no;
	private String memberId;
	private MemberDTO member;
	private int clsId;
	private ClassDTO cls;
	private String chkStart;
	private String chkFinish;
	private Date chkDate;
	
	

}
