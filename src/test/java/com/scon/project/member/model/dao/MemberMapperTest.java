package com.scon.project.member.model.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.scon.project.config.SconApplication;
import com.scon.project.member.model.dto.MemberDTO;

@SpringBootTest
@ContextConfiguration(classes = {SconApplication.class})
public class MemberMapperTest {
	
//	@Autowired
//	private MemberMapper memberMapper;
//
//	@Test
//	@Disabled
//	public void 멤버_등록_매퍼_테스트() {
//		
//		//given
//		MemberDTO member = new MemberDTO();
//		member.setId("user01");
//		member.setName("유관순");
//		member.setPwd("pass01");
//		member.setPhone("010-1234-5678");
//		member.setEmail("you123@gmail.com");
//		
//		String  day = "1990-01-01"; 
//		java.sql.Date brithDay = java.sql.Date.valueOf(day);
//		
//		member.setBirthDay(brithDay);
//		member.setAddress("서울시 강남구 역삼동");
//		member.setGender("F");
//		member.setStatus("대기생");
//
//		//when
//		int result = memberMapper.insertMember(member);
//
//		//then
//		assertEquals(1, result); 
//		
//	}
//	
//	
//	@Test
//	public void 멤버_조회용_매퍼_테스트() {
//		
//		//given
//		String username = "director";
//		
//		//when
//		MemberDTO member = new MemberDTO();
//		member = memberMapper.findMemberById(username);
//		
//		assertNotNull(member);
//		
//
//	}
}
