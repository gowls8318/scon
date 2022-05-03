package com.scon.project.member.model.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MemberRoleDTO {

	private String memberId;
	private String authorityCode;
	
	/* TB_AUTHORITY - 권한 코드별로 가지는 권한을 나타냄 */
	private AuthorityDTO authority;
	
}
