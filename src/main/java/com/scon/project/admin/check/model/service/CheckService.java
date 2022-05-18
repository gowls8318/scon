package com.scon.project.admin.check.model.service;

import java.util.List;

import com.scon.project.admin.Class.dto.ClassDTO;
import com.scon.project.admin.check.model.dto.CheckDTO;
import com.scon.project.member.model.dto.MemberDTO;

public interface CheckService {

	/* 날짜별 반 출석 조회 */
	List<CheckDTO> selectAllClassList(ClassDTO cls, CheckDTO chk, MemberDTO member) throws Exception;
	
	/* 학생별 출석 조회 */
	List<CheckDTO> selectAllStudentList() throws Exception;
	
	/* 강의 목록 조회*/
	List<ClassDTO> selectClassList(ClassDTO cls) throws Exception;

}
