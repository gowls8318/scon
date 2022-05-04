package com.scon.project.admin.Class.service;

import java.util.List;

import com.scon.project.admin.Class.dto.ClassDTO;

public interface ClassService {
	

	boolean registClass(ClassDTO classDTO) throws Exception;

	//강의조회test
	List<ClassDTO> selectClass(); 
	
	//강의상세조회test
	List<ClassDTO> selectAllClass();

}
