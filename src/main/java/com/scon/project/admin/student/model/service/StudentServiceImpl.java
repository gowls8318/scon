package com.scon.project.admin.student.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scon.project.admin.student.model.dao.StudentMapper;
import com.scon.project.admin.student.model.dto.ParentsDTO;
import com.scon.project.admin.student.model.dto.ProfileDTO;
import com.scon.project.admin.student.model.dto.StudentDTO;

@Service("studentService")
public class StudentServiceImpl implements StudentService{

	private StudentMapper studentMapper;
	
	@Autowired
	public StudentServiceImpl(StudentMapper studentMapper) {
		this.studentMapper = studentMapper;
	}
	

	@Override
	public int insertFile(ProfileDTO profile) {
		return studentMapper.insertFile(profile);
	}

	@Override
	public int insertStudent(StudentDTO student) {
		return studentMapper.insertStudent(student);
	}


	@Override
	public int insertParents(ParentsDTO parents) {
		return studentMapper.insertParents(parents);
	}


	@Override
	public int insertProfile(String id) {
		return studentMapper.insertProfile(id);
	}

}
