package com.scon.project.member.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.scon.project.member.model.dto.ProfileDTO;
import com.scon.project.member.model.dto.MemberDTO;

@Mapper
public interface MemberMapper {
	
	//멤버 아이디로 조회
	MemberDTO findMemberById(String username);

	//멤버 등록
	int insertMember(MemberDTO member);
	
	// 권한 등록
	int insertMemberRole(String id, int role);

	// 프로필 사진 등록
	int insertFile(ProfileDTO profile);

	int insertProfile(String id);

	// 아이디 중복 체크
	int checkId(MemberDTO member);

	// 모든 원생 조회 
	List<MemberDTO> findAllStudentList();

	
}
