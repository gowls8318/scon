package com.scon.project.admin.check.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.scon.project.admin.Class.dto.ClassDTO;
import com.scon.project.admin.check.model.dao.CheckMapper;
import com.scon.project.admin.check.model.dto.CheckDTO;

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
	public List<CheckDTO> selectAllClassList() throws Exception {
		
		List<CheckDTO> checkClassList = checkMapper.selectAllClassList();
		
		if(checkClassList == null) {
			throw new Exception("강의 조회에 실패하였습니다.");
		}
		
		return checkClassList;
	}
	
	/* 학생별 출석 조회 */
	@Override
	public List<CheckDTO> selectAllStudentList() throws Exception {
		
		List<CheckDTO> checkStudentList = checkMapper.selectAllStudentList();
		
		return checkStudentList;
	}
	
	/* 강의 목록 조회*/
	@Override
	public List<ClassDTO> selectClassList() throws Exception {
		
		List<ClassDTO> classList = checkMapper.selectClassList();
		
		if(classList == null) {
			throw new Exception("강의 조회에 실패하였습니다.");
		}
		
		return classList;
	}


}
