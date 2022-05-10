package com.scon.project.admin.lecture.model.service;

import java.util.List;

import com.scon.project.admin.Class.dto.ClassDTO;
import com.scon.project.admin.lecture.model.dto.LectureDTO;

public interface LectureService {

	/* 수강 내역 조회용 */
	List<LectureDTO> selectAllLectureList() throws Exception;

	/* 수강 등록 - 전체 강의 조회용 */
	List<ClassDTO> findAllClassList() throws Exception;

	/* 수강 등록 - 강의 정보 조회용 */
	List<ClassDTO> findClassDetail() throws Exception;
	
	/* 수강 등록용 */
	int insertLecture(LectureDTO lec) throws Exception;

	/* 수강 상세 조회용 */
	LectureDTO selectLectureDetail(int no) throws Exception;
	
	/* 수강 수정용 */
	int modifyLecture(LectureDTO lec) throws Exception;

}
