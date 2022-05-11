package com.scon.project.admin.teacher.model.dao;

import org.apache.ibatis.annotations.Mapper;

import com.scon.project.admin.teacher.model.dto.TeacherDTO;

@Mapper
public interface TeacherMapper {

	int insertTeacher(TeacherDTO teacher);

	TeacherDTO findTeacherById(String id);

	int updateTeacher(TeacherDTO teacher);

	int selectTeacherById(TeacherDTO teacher);

}
