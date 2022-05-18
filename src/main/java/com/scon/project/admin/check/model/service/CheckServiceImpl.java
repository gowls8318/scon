package com.scon.project.admin.check.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.scon.project.admin.Class.dto.ClassDTO;
import com.scon.project.admin.check.model.dao.CheckMapper;
import com.scon.project.admin.check.model.dto.CheckDTO;
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
	
	/* 학생별 출석 조회 */
	@Override
	public List<CheckDTO> selectAllStudentList() throws Exception {
		
		List<CheckDTO> checkStudentList = checkMapper.selectAllStudentList();
		
		return checkStudentList;
	}
	
	/* 강의 목록 조회*/
	@Override
	public List<ClassDTO> selectClassList(ClassDTO cls) throws Exception {
		
		return checkMapper.selectClassList(cls);
		
	}


}
