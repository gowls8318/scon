package com.scon.project.admin.student.model.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.scon.project.admin.student.model.dao.StudentMapper;
import com.scon.project.admin.student.model.dto.ParentsDTO;
import com.scon.project.admin.student.model.dto.StudentDTO;
import com.scon.project.member.model.dto.MemberDTO;

@Service("studentService")
public class StudentServiceImpl implements StudentService{

	private StudentMapper studentMapper;
	
	@Autowired
	public StudentServiceImpl(StudentMapper studentMapper) {
		this.studentMapper = studentMapper;
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
	public StudentDTO findStudentById(String id) {
		
		return studentMapper.findStudentById(id);
	}


}
