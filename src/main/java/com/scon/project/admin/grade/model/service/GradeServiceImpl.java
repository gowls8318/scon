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
	
	//성적 수정
	@Override
	public int updateGrade(GradeDTO gradeDTO) throws Exception {
		return gradeMapper.updateGrade(gradeDTO);
	}

	//성적 입력 학생 조회
	@Override
	public List<GradeDTO> findAllStudent(int clsId) {
		return gradeMapper.findAllStudent(clsId);
	}

	//성적 입력
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
	@Override
	public int deleteGrade(List<String> gradeIdArr) throws Exception {
		
		int result = 0;
		
		for(String gradeId : gradeIdArr) { 
			result += gradeMapper.deleteGrade(Integer.parseInt(gradeId));
		}
		
		if(result == gradeIdArr.size()) {
			result = 1;
		} else {
			throw new Exception("성적 삭제 실패");
		}
		
		return result;
	}



	
}
