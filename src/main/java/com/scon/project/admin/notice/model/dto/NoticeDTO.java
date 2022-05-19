package com.scon.project.admin.notice.model.dto;

import java.sql.Date;
import java.util.List;

import com.scon.project.member.model.dto.MemberDTO;

import lombok.Data;

@Data	
public class NoticeDTO {
	
	private int no;
	private String notiTitle;
	private String memberId;
	private String notiContent;
	private Date notiDate;
	private Date notiUpdate;
	private int notiView;
	private String notiDelete;

	
	private MemberDTO member;
	private List<NoticeCmtDTO> comment;
}
