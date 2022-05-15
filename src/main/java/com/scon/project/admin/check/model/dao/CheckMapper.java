package com.scon.project.admin.check.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.scon.project.admin.Class.dto.ClassDTO;
import com.scon.project.admin.check.model.dto.CheckDTO;

@Mapper
public interface CheckMapper {
	
	List<CheckDTO> selectAllClassList();
	
	List<CheckDTO> selectAllStudentList();
	
	List<ClassDTO> selectClassList();

	
}
