package com.scon.project.admin.student.model.dao;

import org.apache.ibatis.annotations.Mapper;

import com.scon.project.admin.student.model.dto.StudentDTO;
import com.scon.project.member.model.dto.MemberDTO;

@Mapper
public interface StudentMapper {
		
		int insertMember(MemberDTO member);

		int insertStudent(StudentDTO student);
		
		MemberDTO findMemberById(String username);
		
}
