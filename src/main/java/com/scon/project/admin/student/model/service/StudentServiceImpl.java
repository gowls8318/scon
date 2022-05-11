package com.scon.project.admin.student.model.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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

	
	@Override
	public int updateStudent(StudentDTO student) {
		
		int result = 0;
		int before = studentMapper.selectStudentById(student);
		
		if(before > 0) {
			result = studentMapper.updateStudent(student);
		} else {
			result = studentMapper.insertStudent(student);
		}
		
		return result;
	}

	
	@Override
	public int updateParents(ParentsDTO parents) {
		
		int result =0;
		int before = studentMapper.selectParentsById(parents);
		
		if(before > 0) {
			result = studentMapper.updateParents(parents);
		}else {
			result = studentMapper.insertParents(parents);
		}
		return result;
	}


}
