package com.scon.project.member.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.scon.project.member.model.dto.ProfileDTO;
import com.scon.project.member.model.dto.MemberDTO;

@Mapper
public interface MemberMapper {
	
	// 회원 아이디로 조회
	MemberDTO findMemberById(String username);
	
	// 아이디 찾기
	String findIdByName(String name, String email);
	
	// 아이디 찾기
	MemberDTO findPwd(MemberDTO member);

	//비밀번호 변경
	int updatePwd(MemberDTO member);

	// 회원 등록
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

	// 모든 강사 조회
	List<MemberDTO> findAllTeacherList();
	
	// 회원 필수정보 수정
	int updateMember(MemberDTO member);

	int updateProfile(String id);

	int selectPofileById(MemberDTO member);

	// 회원 삭제
	int deleteMember(String id, String status);

	




	
}
