package com.scon.project.admin.lecture.model.service;

import java.util.List;

import com.scon.project.admin.Class.dto.ClassDTO;
import com.scon.project.admin.lecture.model.dto.LectureDTO;
import com.scon.project.member.model.dto.MemberDTO;

public interface LectureService {

	/* 수강 내역 조회용 */
	List<LectureDTO> selectAllLectureList() throws Exception;
	
	/* 강의 조회용 */
	List<ClassDTO> selectAllClassList() throws Exception;

	/* 원생 조회용 */
	List<MemberDTO> selectAllMemberList() throws Exception;
	
	/* 수강 등록용 */
	int insertLecture(LectureDTO lec) throws Exception;

}
