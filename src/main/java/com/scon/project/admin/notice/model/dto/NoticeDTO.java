package com.scon.project.admin.notice.model.dto;

import java.sql.Date;

import lombok.Data;

@Data	
public class NoticeDTO {
	
	private int notiNo;
	private String memberId;
	private int clsId;
	private String notiTitle;
	private String notiContent;
	private Date notiDate;
	private Date notiUpdate;
	private int notiView;
	private String notiDelete;
}
