package com.scon.project.member.model.service;

import java.util.Map;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.scon.project.member.model.dto.MemberDTO;

public interface MemberService extends UserDetailsService {

	int insertMember(MemberDTO member);

}
