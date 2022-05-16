package com.scon.project.admin.check.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.scon.project.admin.Class.dto.ClassDTO;
import com.scon.project.admin.check.model.dto.CheckDTO;

@Mapper
public interface CheckMapper {
	
	/* 날짜별 반 출석 조회 */
	List<CheckDTO> selectAllClassList();
	
	/* 학생별 출석 조회 */
	List<CheckDTO> selectAllStudentList();
	
	/* 강의 목록 조회*/
	List<ClassDTO> selectClassList();

	
}
