package com.scon.project.admin.grade.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.scon.project.admin.grade.model.dto.GradeDTO;

@Mapper
public interface GradeMapper {

	List<GradeDTO> findAllGrade();


	
	
}
