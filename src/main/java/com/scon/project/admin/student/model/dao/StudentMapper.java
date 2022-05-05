package com.scon.project.admin.student.model.dao;

import org.apache.ibatis.annotations.Mapper;

import com.scon.project.admin.student.model.dto.ParentsDTO;
import com.scon.project.admin.student.model.dto.ProfileDTO;
import com.scon.project.admin.student.model.dto.StudentDTO;
import com.scon.project.member.model.dto.MemberDTO;

@Mapper
public interface StudentMapper {

		int insertStudent(StudentDTO student);
		
		MemberDTO findMemberById(String username);

		StudentDTO findStudentById(String username);

		int insertFile(ProfileDTO profile);

		int insertParents(ParentsDTO parents);

		int insertProfile(String id);
		
}
