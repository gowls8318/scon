package com.scon.project.admin.grade.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.scon.project.admin.grade.model.dto.GradeDTO;
import com.scon.project.member.model.dto.MemberDTO;

@Mapper
public interface GradeMapper {

	List<GradeDTO> findAllGrade(int classId);

	int insertGradeList(GradeDTO grade);

	List<MemberDTO> findAllStudent(int classId);

	int updateGrade(GradeDTO gradeDTO) throws Exception;
	
//	int deleteGrade(int gradeId) throws Exception;


	
}
