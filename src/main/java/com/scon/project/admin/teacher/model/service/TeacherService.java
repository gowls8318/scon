package com.scon.project.admin.teacher.model.service;

import com.scon.project.admin.teacher.model.dto.TeacherDTO;

public interface TeacherService {

	int insertTeacher(TeacherDTO teacher);
	
	TeacherDTO findTeacherById(String id);

	int updateTeacher(TeacherDTO teacher);


}
