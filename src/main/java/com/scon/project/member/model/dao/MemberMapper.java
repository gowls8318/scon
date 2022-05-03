package com.scon.project.member.model.dao;

import org.apache.ibatis.annotations.Mapper;

import com.scon.project.member.model.dto.MemberDTO;

@Mapper
public interface MemberMapper {

	MemberDTO findMemberById(String username);

	int insertMember(MemberDTO member);
	
}
