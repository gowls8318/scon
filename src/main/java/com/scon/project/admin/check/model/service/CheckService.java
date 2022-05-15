package com.scon.project.admin.check.model.service;

import java.util.List;

import com.scon.project.admin.Class.dto.ClassDTO;
import com.scon.project.admin.check.model.dto.CheckDTO;

public interface CheckService {

	List<CheckDTO> selectAllClassList() throws Exception;
	
	List<CheckDTO> selectAllStudentList() throws Exception;
	
	List<ClassDTO> selectClassList() throws Exception;

}
