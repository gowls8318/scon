package com.scon.project.admin.student.model.dao;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.scon.project.admin.student.model.dto.StudentDTO;
import com.scon.project.config.SconApplication;
import com.scon.project.member.model.dao.MemberMapper;
import com.scon.project.member.model.dto.MemberDTO;

@SpringBootTest
@ContextConfiguration(classes = { SconApplication.class })
public class StudentMapperTest {

	 @Autowired 
	 private StudentMapper studentMapper;
	 private MemberMapper memberMapper;

	@Test
	@Disabled
	public void 매퍼_인터페이스_의존성_주입_테스트() {
		assertNotNull(studentMapper);
	}
	  
	  @Test
	  @Disabled
	  public void 원생_등록_매퍼_테스트_완료() {
	  
	  //given 
	  MemberDTO member = new MemberDTO(); 
	  member.setId("user02");
	  member.setName("유관순"); 
	  member.setPassword("pass01");
	  member.setPhone("010-1234-5678"); 
	  member.setEmail("you123@gmail.com");
	  
	  String day = "1990-01-01"; 
	  java.sql.Date brithDay = java.sql.Date.valueOf(day);
	  member.setBirthDay(brithDay); 
	  member.setAddress("서울시 강남구 역삼동");
	  member.setGender("F"); member.setStatus("대기생");
	  
	  StudentDTO student = new StudentDTO(); 
	  student.setId(member.getId());
	  student.setStudentType("고등학생"); 
	  student.setSchoolName("스프링고등학교");
	  student.setSchoolGrade("3"); 
	  student.setSchoolClass("mvc-2반");
	  student.setConsult("스프링을 잘 이해하는 우수한 학생임.");
	  
	  //when 
//	  int result1 = memberMapper.insertMember(member);
//	  int result2 = studentMapper.insertStudent(student);
	  
	 // then 
//	  assertEquals(1, result1);
//	  assertEquals(1, result2);
	  }

	 @Test
	 @Disabled
	 public void 원생_조회용_매퍼_테스트_완료() {
	 
	 //given 
	String username = "user01";

	 //when 
	 MemberDTO member = new MemberDTO(); 
	// member = studentMapper.findMemberById(username);
	 
	 StudentDTO student = studentMapper.findStudentById(username);
	 
	 assertNotNull(member);
	 assertNotNull(student);
	 
	 }


}
