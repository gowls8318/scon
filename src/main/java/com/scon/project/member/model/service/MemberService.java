package com.scon.project.member.model.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.scon.project.member.model.dto.ProfileDTO;
import com.scon.project.member.model.dto.MemberDTO;

public interface MemberService extends UserDetailsService {

	int insertMember(MemberDTO member, ProfileDTO profile, int role);

	int checkId(MemberDTO member);

	List<MemberDTO> findAllStudentList();


}
