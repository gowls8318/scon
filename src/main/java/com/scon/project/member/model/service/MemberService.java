package com.scon.project.member.model.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.scon.project.member.model.dto.ProfileDTO;
import com.scon.project.admin.main.model.dto.StudentCount;
import com.scon.project.member.model.dto.MemberDTO;

public interface MemberService extends UserDetailsService {

	int insertMember(MemberDTO member, int role);

	int insertProfile(ProfileDTO profile, MemberDTO member);
	
	int checkId(MemberDTO member);

	List<MemberDTO> findAllStudentList(MemberDTO member);

	List<MemberDTO> findAllTeacherList(MemberDTO member);
	
	MemberDTO selectMember(String id);
	
	int updateMember(MemberDTO member);

	int updateProfile(ProfileDTO profile, MemberDTO member);

	int deleteMember(String id, String status);

	String findIdByName(String name, String email);

	MemberDTO findPwd(MemberDTO member);

	int updatePassword(MemberDTO member);

	String findPwdById(String id);
	
	//원생 수 추이
	StudentCount findStudentCount();


}
