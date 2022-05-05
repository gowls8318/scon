package com.scon.project.admin.grade.model.service;

import java.util.List;

import com.scon.project.admin.grade.model.dto.GradeDTO;
import com.scon.project.member.model.dto.MemberDTO;

public interface GradeService {

	List<GradeDTO> findAllGrade(int clsId);

	boolean insertGradeList(GradeDTO grade) throws Exception;

	List<MemberDTO> findAllStudent(int clsId);

	int updateGrade(GradeDTO gradeDTO) throws Exception;

	//int deleteGrade(int gradeId) throws Exception;



}
