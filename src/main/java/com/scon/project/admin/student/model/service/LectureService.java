package com.scon.project.admin.student.model.service;

import java.util.List;

import com.scon.project.admin.Class.dto.ClassDTO;
import com.scon.project.admin.student.model.dto.LectureDTO;

public interface LectureService {

	/* 수강 내역 조회용 */
	List<LectureDTO> selectAllLectureList() throws Exception;

	/* 전체 강의 조회용 */
	List<ClassDTO> findAllClassList() throws Exception;

	/* 수강 등록용 */
	int insertLecture(LectureDTO lec) throws Exception;

}
