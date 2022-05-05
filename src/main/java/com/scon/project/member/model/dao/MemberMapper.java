package com.scon.project.member.model.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.scon.project.member.model.dto.MemberDTO;

@Mapper
public interface MemberMapper {
	
	//멤버 조회
	MemberDTO findMemberById(String username);

	//멤버 등록
	int insertMember(MemberDTO member);
	
}
