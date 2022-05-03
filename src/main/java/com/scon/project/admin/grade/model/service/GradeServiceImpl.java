package com.scon.project.admin.grade.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scon.project.admin.grade.model.dao.GradeMapper;
import com.scon.project.admin.grade.model.dto.GradeDTO;

@Service("gradeService")
public class GradeServiceImpl implements GradeService{

	
	private final GradeMapper gradeMapper;
	
	@Autowired
	public GradeServiceImpl(GradeMapper gradeMapper) {
		this.gradeMapper = gradeMapper;
	}
	
	@Override
	public List<GradeDTO> findAllGrade() {
		return gradeMapper.findAllGrade();
	}
	
	
	
	
	
	
	
}
