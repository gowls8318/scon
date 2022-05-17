package com.scon.project.admin.grade.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.scon.project.admin.grade.model.dto.GradeDTO;
import com.scon.project.member.model.dto.MemberDTO;

@Mapper
public interface GradeMapper {

	//학생 성적 조회(complete)
	List<GradeDTO> findAllGrade(int clsId);
	
	//성적 수정(complete)
	int updateGrade(GradeDTO gradeDTO) throws Exception;

	//성적 입력 전 학생 조회 (complete)
	List<GradeDTO> findAllStudent(int clsId);

	//성적 입력 (complete)
	int insertGradeList(GradeDTO grade);


//	int deleteGrade(int[] gradeId) throws Exception;

	//성적 삭제 (테스트 중! 이 메소드 쓰기!!!!)
	int deleteGrade(List<String> deleteList);




	
}
