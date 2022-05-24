package com.scon.project.admin.check.model.service;

import java.util.List;

import com.scon.project.admin.Class.dto.ClassDTO;
import com.scon.project.admin.check.model.dto.CheckDTO;
import com.scon.project.admin.check.model.dto.CheckListDTO;
import com.scon.project.admin.lecture.model.dto.LectureDTO;
import com.scon.project.member.model.dto.MemberDTO;

public interface CheckService {

	/* 날짜별 반 출석 조회 */
	List<CheckDTO> selectAllClassList(ClassDTO cls, CheckDTO chk, MemberDTO member) throws Exception;
	
	/* 결석 학생 조회 */
	List<CheckDTO> selectAllAbsentList(ClassDTO cls, CheckDTO chk, MemberDTO member) throws Exception;
	
	/* 강의 목록 조회*/
	List<ClassDTO> selectClassList(ClassDTO cls) throws Exception;

	/* 출석 등록 목록 불러오기 */
	List<LectureDTO> selectChkList(ClassDTO cls, LectureDTO lec, MemberDTO member) throws Exception;

	/* 출석 등록하기 */
	int insertChkList(CheckListDTO chk) throws Exception;
	
	/* 출석 수정하기 */
	int updateChkList(CheckListDTO chk) throws Exception;

}
