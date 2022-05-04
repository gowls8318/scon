package com.scon.project.admin.academy.dao;

import org.apache.ibatis.annotations.Mapper;

import com.scon.project.admin.academy.dto.AcademyDTO;

@Mapper
public interface AcademyMapper {
	
	AcademyDTO selectAcademyInfo();

}
