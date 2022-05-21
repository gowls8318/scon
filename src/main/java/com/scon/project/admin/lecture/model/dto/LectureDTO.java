package com.scon.project.admin.lecture.model.dto;

import com.scon.project.admin.Class.dto.ClassDTO;
import com.scon.project.member.model.dto.MemberDTO;

import lombok.Data;

@Data
public class LectureDTO {

	private int no;						// 수강번호
	private String memberId;			// 회원아이디
	private MemberDTO member;
	private int clsId;					// 강의아이디
	private ClassDTO cls;
	private int lecPay;					// 수강료
	private String lecStatus;			// 수강상태
	private String lecDiscount;			// 할인사유
	private String lecDelete;			// 삭제여부
	private String accStatus;			// 수납여부
	private String accDate;				// 수납일
	private String accOption;			// 결제방법
	private RefundDTO ref;
	
}
