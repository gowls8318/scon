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
	
	@Override
	public List<GradeDTO> findAllGrade(int clsId) {
		return gradeMapper.findAllGrade(clsId);
	}


//	@Override
//	public boolean insertGradeList(GradeDTO grade) throws Exception {
//		
//		int result = gradeMapper.insertGradeList(grade);
//		
//		if(result <= 0) {
//			throw new Exception("성적 등록에 실패하셨습니다.");
//		} 
//		
//		return result > 0 ? true : false;
//	}

//	@Override
//	public boolean insertGradeList() {
//		
//		int result = gradeMapper.insertGradeList(clsId, memberId);
//		
//		if(result <= 0) {
//			throw new Exception("학생 등록에 실패하셨습니다.");
//		}
//		
//		return result > 0 ? true : false;
//	}

	/* 성적 입력 학생 조회 */
	@Override
	public List<MemberDTO> findAllStudent(int classId) {
		return gradeMapper.findAllStudent(classId);
	}
	
	@Override
	public boolean insertGradeList(GradeDTO grade) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}



	
	
	
	
	
	
	
}
