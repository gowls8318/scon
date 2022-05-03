package com.scon.project.admin.consultant.model.dto;

import java.sql.Date;

//import com.scon.project.member.model.dto.MemberDTO;

import lombok.Data;

@Data
public class ConsultantDTO {

	private int conNo;
	private String memberId;
//	private MemberDTO member;
	private Date conWriteDate;
	private Date conHDate;
	private String conHTime;
	private String conHTitle;
	private String conHContent;
	private Date conHUpdate;
	private String conHDelete;
	private String conHandle;
	private Date conDate;
	private String conType;
	private String conWay;
	private int conImp;
	private String conContent;
	private Date conUpdate;
	private String conDelete;
	
}
