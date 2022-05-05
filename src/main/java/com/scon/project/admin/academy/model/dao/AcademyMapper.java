package com.scon.project.admin.academy.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.scon.project.admin.academy.model.dto.AcademyDTO;

@Mapper
public interface AcademyMapper {
	
	AcademyDTO selectAcademyInfo();
	

}
