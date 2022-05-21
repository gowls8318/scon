package com.scon.project.admin.lecture.model.dto;

import lombok.Data;

@Data
public class RefundDTO {

	private int no;						// 환불번호
	private int lecNo;					// 수강번호
	private int refPay;					// 환불금액
	private String refDate;				// 환불일자
	private String refContent;			// 환불사유
	
}
