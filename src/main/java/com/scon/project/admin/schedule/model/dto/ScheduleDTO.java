package com.scon.project.admin.schedule.model.dto;

import lombok.Data;

@Data	//매개변수 생성자를 제외하고 위의 내용을 한 번에 처리할 수 있는 어노테이션
public class ScheduleDTO {
	
	private int scheNo;
	private String memberId;
	private int clsId;
	private String day;
	private String scheContent;
	private String scheColor;
	private String scheDelete;
	
	
}
