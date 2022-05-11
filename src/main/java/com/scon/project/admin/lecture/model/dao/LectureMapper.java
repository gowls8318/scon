package com.scon.project.admin.lecture.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.scon.project.admin.Class.dto.ClassDTO;
import com.scon.project.admin.lecture.model.dto.LectureDTO;
import com.scon.project.member.model.dto.MemberDTO;

@Mapper
public interface LectureMapper {

	/* 강의 내역 조회용 */
	List<LectureDTO> selectAllLectureList();
	
	/* 강의 조회용 */
	List<ClassDTO> selectAllClassList();

	/* 원생 조회용 */
	List<MemberDTO> selectAllMemberList();
	
	/* 수강 등록용 */
	int insertLecture(LectureDTO lec);

}
