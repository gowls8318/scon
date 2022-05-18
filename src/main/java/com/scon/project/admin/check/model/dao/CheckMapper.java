package com.scon.project.admin.check.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.scon.project.admin.Class.dto.ClassDTO;
import com.scon.project.admin.check.model.dto.CheckDTO;
import com.scon.project.member.model.dto.MemberDTO;

@Mapper
public interface CheckMapper {
	
	/* 출석 조회 */
	List<CheckDTO> selectAllClassList(ClassDTO cls, CheckDTO chk, MemberDTO member);
	
	/* 결석 조회 */
	List<CheckDTO> selectAllStudentList();
	
	/* 강의 목록 조회*/
	List<ClassDTO> selectClassList(ClassDTO cls);

	
}
