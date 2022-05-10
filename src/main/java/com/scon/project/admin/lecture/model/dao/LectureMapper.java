package com.scon.project.admin.lecture.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.scon.project.admin.Class.dto.ClassDTO;
import com.scon.project.admin.lecture.model.dto.LectureDTO;

@Mapper
public interface LectureMapper {

	/* 수강 내역 조회용 */
	List<LectureDTO> selectAllLectureList();

	/* 수강 등록 - 전체 강의 조회용 */
	List<ClassDTO> findAllClassList();

	/* 수강 등록 - 강의 정보 조회용 */
	List<ClassDTO> findClassDetail();
	
	/* 수강 등록용 */
	int insertLecture(LectureDTO lec);

	/* 수강 상세 조회용 */
	LectureDTO selectLectureDetail(int no);
	
	/* 수강 수정용 */
	int modifyLecture(LectureDTO lec);


}
