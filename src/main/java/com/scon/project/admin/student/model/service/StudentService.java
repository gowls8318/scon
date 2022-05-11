package com.scon.project.admin.student.model.service;

import com.scon.project.admin.student.model.dto.ParentsDTO;

import com.scon.project.admin.student.model.dto.StudentDTO;
import com.scon.project.member.model.dto.MemberDTO;


public interface StudentService {

	int insertStudent(StudentDTO student);
	
	int insertParents(ParentsDTO parents);

	StudentDTO findStudentById(String id);

	int updateStudent(StudentDTO student);

	int updateParents(ParentsDTO parents);


	

}
