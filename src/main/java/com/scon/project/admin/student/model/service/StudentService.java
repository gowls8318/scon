package com.scon.project.admin.student.model.service;

import com.scon.project.admin.student.model.dto.ParentsDTO;
import com.scon.project.admin.student.model.dto.ProfileDTO;
import com.scon.project.admin.student.model.dto.StudentDTO;


public interface StudentService {

	int insertStudent(StudentDTO student);
	
	int insertParents(ParentsDTO parents);

	int insertFile(ProfileDTO profile);

	int insertProfile(String id);

	

}
