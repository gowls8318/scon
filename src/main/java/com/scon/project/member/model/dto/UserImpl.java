package com.scon.project.member.model.dto;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;
import lombok.ToString;;

@Getter
@ToString
public class UserImpl extends User {
	
	private String id;								//회원아이디
	private String name;							//회원명
	private String pwd;								//비밀번호
	private String tempPwdYN;						//임시 비밀번호 여부
	private String phone;							//연락처
	private String email;							//이메일
	private java.sql.Date birthDay;					//생년월일
	private String address;							//주소
	private String gender;							//성별
	private java.sql.Date enrollDate;				//등록일
	private java.sql.Date delDate;				//삭제일
	private String status;							//회원상태

	/* TB_MEMBER_ROLE - 한 멤버는 여러 권한을 가질 수 있다 */
	private List<MemberRoleDTO> memberRoleList;		//권한 목록

	public UserImpl(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}
	
	public void setDetails(MemberDTO member) {
		this.id = member.getId();
		this.name = member.getName();
		this.pwd = member.getPassword();
		this.tempPwdYN = member.getTempPwdYN();
		this.phone = member.getPhone();
		this.email = member.getEmail();
		this.birthDay = member.getBirthDay();
		this.address = member.getAddress();
		this.gender = member.getGender();
		this.enrollDate = member.getEnrollDate();
		this.delDate = member.getDelDate();
		this.status = member.getStatus();
		this.memberRoleList = member.getMemberRoleList();
		
	}

	
	
}
