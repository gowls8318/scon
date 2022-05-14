package com.scon.project.admin.Class.dto;

import java.util.List;
import com.scon.project.member.model.dto.MemberDTO;

import lombok.Data;

@Data
public class ClassDTO {
	
	/*
		CLS_ID	NUMBER	No		1	강의아이디
		MEMBER_ID	VARCHAR2(30 BYTE)	No		2	강사아이디
		CLS_NAME	VARCHAR2(200 BYTE)	No		3	강의명
		CLS_SUBJECT	VARCHAR2(200 BYTE)	Yes		4	과목명
		CLS_STUNUM	VARCHAR2(10 BYTE)	Yes		5	실강생수
		CLS_GRADE	VARCHAR2(30 BYTE)	Yes		6	대상학년
		CLS_PAY	NUMBER	No	"0	"	7	수업료
		CLS_ROOM	VARCHAR2(50 BYTE)	No		8	강의실
		CLS_START	VARCHAR2(20 BYTE)	No		9	수업시작일
		CLS_END	VARCHAR2(20 BYTE)	No		10	수업종료일
		CLS_NOTE	VARCHAR2(3000 BYTE)	Yes		11	비고란
		CLS_STATUS	VARCHAR2(2 BYTE)	Yes	"'Y'	"	12	강의 개설 유무
	 */
	
	private int clsId; 
	private String memberId;
	private MemberDTO member;
	private String clsName;
	private String clsSubject;
	private String clsStuNum;
	private String clsGrade;
	private int clsPay;
	private String clsRoom;
	private String clsStart;
	private String clsEnd;
	private String clsNote;
	private String clsStatus;
	private List<DayDTO> dayList;
	private List<TimeDTO> time;
	
		
	

}
