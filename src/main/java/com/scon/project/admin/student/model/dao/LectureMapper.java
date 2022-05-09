package com.scon.project.admin.student.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.scon.project.admin.Class.dto.ClassDTO;
import com.scon.project.admin.student.model.dto.LectureDTO;

@Mapper
public interface LectureMapper {

	/* 수강 내역 조회용 */
	List<LectureDTO> selectAllLectureList();

	/* 전체 강의 조회용 */
	List<ClassDTO> findAllClassList();

	/* 강의 상세 조회용 */
	ClassDTO selectClassDetail(int id);
	
	/* 수강 등록용 */
	int insertLecture(LectureDTO lec);

}
