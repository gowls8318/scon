package com.scon.project.admin.check.model.service;

import java.util.List;

import com.scon.project.admin.Class.dto.ClassDTO;
import com.scon.project.admin.check.model.dto.CheckDTO;

public interface CheckService {

	/* 날짜별 반 출석 조회 */
	List<CheckDTO> selectAllClassList() throws Exception;
	
	/* 학생별 출석 조회 */
	List<CheckDTO> selectAllStudentList() throws Exception;
	
	/* 강의 목록 조회*/
	List<ClassDTO> selectClassList() throws Exception;

}
