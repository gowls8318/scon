package com.scon.project.member.model.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.scon.project.member.model.dao.MemberMapper;
import com.scon.project.member.model.dto.AuthorityDTO;
import com.scon.project.member.model.dto.MemberDTO;
import com.scon.project.member.model.dto.MemberRoleDTO;
import com.scon.project.member.model.dto.ProfileDTO;
import com.scon.project.member.model.dto.UserImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MemberServiceImpl implements MemberService{
	
	private MemberMapper memberMapper;
	private PasswordEncoder passwordEncoder;

	@Autowired
	public MemberServiceImpl(MemberMapper memberMapper,PasswordEncoder passwordEncoder) {
		this.memberMapper = memberMapper;
		this.passwordEncoder = passwordEncoder;
	}

	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		MemberDTO member = memberMapper.findMemberById(username);
		/* null 값이 없게 하기 위해 조회 된 값이 없을 시 빈 객체 */
		if(member == null) member = new MemberDTO();
		
		log.info("로그인 유저 : {}", member);
		
		/* 권한 리스트 */
		List<GrantedAuthority> authorities = new ArrayList<>();
		
		if(member != null && member.getMemberRoleList() != null) {
			
			for(MemberRoleDTO role : member.getMemberRoleList()) {
				AuthorityDTO authority = role.getAuthority();
				
				if(authority != null) {
					authorities.add(new SimpleGrantedAuthority(authority.getName()));
				}
			}
		}
		
		log.info("로그인 권한 : {}", authorities);
		
		
		// return new User(member.getId(), member.getPwd(), authorities);
		
		/* User 객체에 담기지 않는 추가 정보를 User 객체를 extends한 UserImpl에 담아서 리턴한다. */
		UserImpl user = new UserImpl(member.getId(), member.getPassword(), authorities);
		user.setDetails(member);
		
		return user;
	}

	@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.SERIALIZABLE, rollbackFor = {Exception.class})
	@Override
	public int insertMember(MemberDTO member, int role){
		
		/* 멤버 등록 */
		int memberResult = memberMapper.insertMember(member);
		/* 멤버 권한 등록 */
		int roleResult = memberMapper.insertMemberRole(member.getId(), role);
		
		int result = 0;
		
		if(memberResult > 0 && roleResult > 0 ) {
			result = 1;
		}

		return result;
		
	}



	@Override
	public int checkId(MemberDTO member) {
		return memberMapper.checkId(member);
	}


	@Override
	public List<MemberDTO> findAllStudentList() {
		
		return memberMapper.findAllStudentList();
	}

	@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.SERIALIZABLE, rollbackFor = {Exception.class})
	@Override
	public int insertProfile(ProfileDTO profile, MemberDTO member) {
		
		int result = 0;
		/* 프로필 사진 있을 시 프로필 사진 등록 */
		if(profile.getFileSaveName() != null) {
			int fileResult = memberMapper.insertFile(profile);
			int profileResult =memberMapper.insertProfile(member.getId());
			
			if(fileResult > 0 && profileResult > 0) {
				result = 1;
			}
		}
		return result;
	}


	@Override
	public int updateMember(MemberDTO member) {

		return 0;
	}






}
