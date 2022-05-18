package com.scon.project.admin.grade.model.service;

import java.util.List;

import com.scon.project.admin.grade.model.dto.GradeDTO;
import com.scon.project.admin.grade.model.dto.GradeDTOList;
import com.scon.project.member.model.dto.MemberDTO;

public interface GradeService {

	//성적 조회
	List<GradeDTO> findAllGrade(int clsId);

	//성적 입력 전 학생 조회 (O)
	List<GradeDTO> findAllStudent(int clsId);
	
	//성적 입력 (O)
	boolean insertGradeList(List<GradeDTO> gradeDTO) throws Exception;

	//성적 수정(O)
	int updateGrade(GradeDTO gradeDTO) throws Exception;


//	int deleteGrade(int[] gradeId) throws Exception;

	//성적 삭제 (테스트 중 이 메소드 쓰기!!!!)
	int deleteGrade(List<String> gradeIdArr) throws Exception;





}
