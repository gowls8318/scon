package com.scon.project.admin.teacher.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.scon.project.admin.teacher.model.dao.TeacherMapper;
import com.scon.project.admin.teacher.model.dto.TeacherDTO;

@Service("teacherService")
public class TeacherServiceImpl implements TeacherService  {

	private TeacherMapper teacherMapper;
	
	@Autowired
	public TeacherServiceImpl(TeacherMapper teacherMapper) {
		this.teacherMapper = teacherMapper;
	}
	
	@Override
	public int insertTeacher(TeacherDTO teacher) {
		
		return teacherMapper.insertTeacher(teacher);
	}

	@Override
	public TeacherDTO findTeacherById(String id) {
		
		return teacherMapper.findTeacherById(id);
	}


	@Override
	public int updateTeacher(TeacherDTO teacher) {
		
		int result = 0;
		int before = teacherMapper.selectTeacherById(teacher);
		
		if(before > 0) {
			result = teacherMapper.updateTeacher(teacher);
		} else {
			result = teacherMapper.insertTeacher(teacher);
		}
		
		return result;
	}

}
