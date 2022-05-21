package com.scon.project.admin.consultant.model.dto;

import java.sql.Date;

import com.scon.project.member.model.dto.MemberDTO;

import lombok.Data;

@Data
public class ConsultantDTO {

	private int no;						// 상담번호
	private String memberId;			// 회원아이디
	private MemberDTO member;
	private Date conWriteDate;			// 작성일
	private String conHDate;			// 상담희망일
	private String conHTime;			// 상담희망시간
	private String conHTitle;			// 상담신청제목
	private String conHContent;			// 상담신청내용
	private Date conHUpdate;			// 상담신청수정일
	private String conHDelete;			// 신청삭제여부
	private String conHandle;			// 처리현황
	private String conDate;				// 상담일
	private String conType;				// 상담유형
	private String conWay;				// 상담방법
	private String conImp;				// 중요도
	private String conContent;			// 상담일지내용
	private Date conUpdate;				// 상담일지수정일
	private String conDelete;			// 일지삭제여부
	
}
