package com.scon.project.admin.check.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class CheckListDTO {
	private int no[];
	private int lecNo[];
	private String chkStart[];
	private String chkFinish[];
//	@DateTimeFormat(pattern="yyyy-MM-dd") 
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd") 
	private String chkDate[];

}
