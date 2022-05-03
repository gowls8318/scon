package com.scon.project.admin.grade.model.dto;

import com.scon.project.admin.Class.dto.ClassDTO;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class GradeDTO {
	
	private String gradeId;
 // private MemberDTO memberId;
	private ClassDTO clsId;
	private java.sql.Date gradeDate;
	private int grade;

	
}
