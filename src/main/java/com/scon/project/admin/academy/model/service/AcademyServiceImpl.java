package com.scon.project.admin.academy.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.scon.project.admin.academy.model.dao.AcademyMapper;
import com.scon.project.admin.academy.model.dto.AcademyDTO;

@Service("academyService")
@Transactional
public class AcademyServiceImpl implements AcademyService {
	
	private final AcademyMapper academyMapper;
	
	@Autowired
	public AcademyServiceImpl(AcademyMapper academyMapper) {
		this.academyMapper = academyMapper;
	}

	@Override
	public AcademyDTO selectAcademyInfo() {
		
		return academyMapper.selectAcademyInfo();
	}
}
