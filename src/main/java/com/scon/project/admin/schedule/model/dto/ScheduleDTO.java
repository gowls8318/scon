package com.scon.project.admin.schedule.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data	//매개변수 생성자를 제외하고 위의 내용을 한 번에 처리할 수 있는 어노테이션
public class ScheduleDTO {
	
	/*  SCHE_NO	NUMBER	No		1	일정번호
		MEMBER_ID	VARCHAR2(100 BYTE)	Yes		2	작성자ID
		CLS_ID	NUMBER	Yes		3	강의ID
		START_DAY	DATE	Yes		4	일정날짜
		SCHE_CONTENT	VARCHAR2(400 BYTE)	Yes		5	일정내용
		SCHE_COLOR	VARCHAR2(100 BYTE)	Yes		6	일정색상
		SCHE_DELETE	VARCHAR2(10 BYTE)	Yes	"'N'	"	7	일정삭제여부
		END_DAY	DATE	No		8	*/
	
	private int scheNo;
	private String memberId;
	private int clsId;
	private String startDay;
	private String endDay;
	private String content;
	private String color;
	private String status;
	

}
