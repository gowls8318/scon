package com.scon.project.admin.lecture.model.dto;

import com.scon.project.admin.Class.dto.ClassDTO;
import com.scon.project.member.model.dto.MemberDTO;

import lombok.Data;

@Data
public class LectureDTO {

	private int no;
	private String memberId;
	private MemberDTO member;
	private int clsId;
	private ClassDTO cls;
	private int lecPay;
	private String lecStatus;
	private String lecDiscount;
	private String lecDelete;
	private String accStatus;
	private String accDate;
	private String accOption;
	private Integer accPay;
	
}
