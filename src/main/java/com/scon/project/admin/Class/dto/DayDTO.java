package com.scon.project.admin.Class.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DayDTO {
	
	/*
		CLS_DAY_ID	    NUMBER	No		1	강의요일아이디
		CLS_DAY_NAME	VARCHAR2(10 BYTE)	Yes		2	요일(월~일)
	 */
	
	private int clsDayId;
	private String clsDayName;
	

}
