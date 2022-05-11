package com.scon.project.member.model.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.scon.project.member.model.dto.ProfileDTO;
import com.scon.project.member.model.dto.MemberDTO;

public interface MemberService extends UserDetailsService {

	int insertMember(MemberDTO member, int role);

	int insertProfile(ProfileDTO profile, MemberDTO member);
	
	int checkId(MemberDTO member);

	List<MemberDTO> findAllStudentList();

	List<MemberDTO> findAllTeacherList();
	
	MemberDTO selectMember(String id);
	
	int updateMember(MemberDTO member);

	int updateProfile(ProfileDTO profile, MemberDTO member);

	int deleteMember(String id, String status);


}
