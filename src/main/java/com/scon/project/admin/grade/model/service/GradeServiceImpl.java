package com.scon.project.admin.grade.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.scon.project.admin.grade.model.dao.GradeMapper;
import com.scon.project.admin.grade.model.dto.GradeDTO;
import com.scon.project.member.model.dto.MemberDTO;

@Service("gradeService")
@Transactional
public class GradeServiceImpl implements GradeService{

	private final GradeMapper gradeMapper;
	
	@Autowired
	public GradeServiceImpl(GradeMapper gradeMapper) {
		this.gradeMapper = gradeMapper;
	}
	
	/* 성적 조회 (complete) */
	@Override
	public List<GradeDTO> findAllGrade(int clsId) {
		return gradeMapper.findAllGrade(clsId);
	}
	
	/* 성적 수정 (complete) */
	@Override
	public int updateGrade(GradeDTO gradeDTO) throws Exception {
		return gradeMapper.updateGrade(gradeDTO);
	}

	/* 성적 입력 학생 조회 (complete) */
	@Override
	public List<GradeDTO> findAllStudent(int classId) {
		return gradeMapper.findAllStudent(classId);
	}

	/* 성적 입력 (complete) */
	@Override
	public boolean insertGradeList(List<GradeDTO> gradeList) throws Exception {
		
		int result = 0;
		
		for(GradeDTO grade : gradeList ){
			result += gradeMapper.insertGradeList(grade);
		}
		
		if(result != gradeList.size()) {
			throw new Exception("성적 등록에 실패하셨습니다.");
		}
		
		return result > 0 ? true : false;
		
	} 


	//성적 삭제
//	@Override
//	public int deleteGrade(int[] gradeId) throws Exception {
//		return gradeMapper.deleteGrade(gradeId);
//	}

	//성적 삭제 (테스트 중 이 메소드 쓰기!!!!!)
	@Override
	public int deleteGrade(List<String> deleteList) {
		return gradeMapper.deleteGrade(deleteList);
	}



	
}
