package com.scon.project.admin.consultant.model.dto;

import java.sql.Date;

import com.scon.project.member.model.dto.MemberDTO;

import lombok.Data;

@Data
public class ConsultantDTO {

	private int no;
	private String memberId;
	private MemberDTO member;
	private Date conWriteDate;
	private String conHDate;
	private String conHTime;
	private String conHTitle;
	private String conHContent;
	private Date conHUpdate;
	private String conHDelete;
	private String conHandle;
	private String conDate;
	private String conType;
	private String conWay;
	private String conImp;
	private String conContent;
	private Date conUpdate;
	private String conDelete;
	
}
