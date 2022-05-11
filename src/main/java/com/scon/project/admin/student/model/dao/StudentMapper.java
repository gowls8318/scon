package com.scon.project.admin.student.model.dao;

import org.apache.ibatis.annotations.Mapper;

import com.scon.project.admin.student.model.dto.ParentsDTO;
import com.scon.project.admin.student.model.dto.StudentDTO;
import com.scon.project.member.model.dto.MemberDTO;

@Mapper
public interface StudentMapper {

		int insertStudent(StudentDTO student);

		int insertParents(ParentsDTO parents);

		StudentDTO findStudentById(String id);

		int updateStudent(StudentDTO student);

		int updateParents(ParentsDTO parents);

		int selectStudentById(StudentDTO student);

		int selectParentsById(ParentsDTO parents);
		
}
