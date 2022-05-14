package com.scon.project.admin.check.model.service;

import java.util.List;

import com.scon.project.admin.check.model.dto.CheckDTO;

public interface CheckService {

	List<CheckDTO> selectAllStudentList() throws Exception;
	
	

}
