package com.scon.project.admin.Class.dao;

import org.apache.ibatis.annotations.Mapper;

import com.scon.project.admin.Class.dto.ClassDTO;

@Mapper
public interface ClassMapper {
	
	int registClass(ClassDTO classDTO);

}
