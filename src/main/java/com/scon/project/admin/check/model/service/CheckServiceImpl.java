package com.scon.project.admin.check.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.scon.project.admin.check.model.dao.CheckMapper;
import com.scon.project.admin.check.model.dto.CheckDTO;

@Service("checkService")
@Transactional
@Component
public class CheckServiceImpl implements CheckService {
	
	private final CheckMapper checkMapper;
	
	@Autowired
	public CheckServiceImpl(CheckMapper checkMapper) {
		this.checkMapper = checkMapper;
	}
	
	@Override
	public List<CheckDTO> selectAllStudentList() throws Exception {
		
		List<CheckDTO> checkList = checkMapper.selectAllStudentList();
		
		return checkList;
	}

}
