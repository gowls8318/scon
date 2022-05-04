package com.scon.project.admin.Class.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TimeDTO {

	/*
	 * CLS_TIME_ID NUMBER No 1 교시코드 CLS_TIME_NAME VARCHAR2(10 BYTE) Yes 2교시(1교시~5교시)
	 * CLS_ID NUMBER No 1 강의아이디 CLS_TIME_ID NUMBER No 2 교시코드
	 */

	private int clsTimeId;
	private String clsTimeName;

}
