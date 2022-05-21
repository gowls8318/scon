package com.scon.project.admin.check.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.scon.project.admin.Class.dto.ClassDTO;
import com.scon.project.admin.business.model.dto.BusinessDTO;
import com.scon.project.admin.check.model.dao.CheckMapper;
import com.scon.project.admin.check.model.dto.CheckDTO;
import com.scon.project.admin.lecture.model.dto.LectureDTO;
import com.scon.project.member.model.dto.MemberDTO;

@Service("checkService")
@Transactional
public class CheckServiceImpl implements CheckService {
	
	private final CheckMapper checkMapper;
	
	@Autowired
	public CheckServiceImpl(CheckMapper checkMapper) {
		this.checkMapper = checkMapper;
	}
	
	/* 날짜별 반 출석 조회 */
	@Override
	public List<CheckDTO> selectAllClassList(ClassDTO cls, CheckDTO chk, MemberDTO member) throws Exception {
		
		return checkMapper.selectAllClassList(cls, chk, member);
		
	}
	
	/* 날짜별 반 출석 조회 */
	@Override
	public List<CheckDTO> selectAllAbsentList(ClassDTO cls, CheckDTO chk, MemberDTO member) throws Exception {
		
		return checkMapper.selectAllAbsentList(cls, chk, member);
		
	}
	
	/* 강의 목록 조회*/
	@Override
	public List<ClassDTO> selectClassList(ClassDTO cls) throws Exception {
		
		return checkMapper.selectClassList(cls);
		
	}
	
	/* 출석 등록 명단 조회 */
	@Override
	public List<LectureDTO> selectChkList(ClassDTO cls, LectureDTO lec, MemberDTO member) throws Exception {
		
		
		return checkMapper.selectChkList(cls, lec, member);
	
		
	}
	
	/* 출석 등록 */
	@Override
	public int insertChkList(CheckDTO chk) throws Exception {
		
		int result = checkMapper.insertChkList(chk);
		
		return result;
	}
	
	
	
	/* 출석 수정 */
	@Override
	public int updateChkList(CheckDTO chk) throws Exception {
		
		int result = checkMapper.updateChkList(chk);
		
		return result;
	}


}
