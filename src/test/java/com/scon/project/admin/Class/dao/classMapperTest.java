package com.scon.project.admin.Class.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.scon.project.admin.Class.dto.ClassDTO;
import com.scon.project.admin.Class.dto.DayDTO;
import com.scon.project.admin.Class.dto.TimeDTO;
import com.scon.project.config.SconApplication;
import com.scon.project.member.model.dto.MemberDTO;

@SpringBootTest
@ContextConfiguration(classes = {SconApplication.class})
public class classMapperTest {
	
	@Autowired
	private ClassMapper classMapper;
	
	@Test
	public void 인터페이스_의존성_주입_테스트() {
		
		assertNotNull(classMapper);
	}
	
	// allarug로 인해 test 생성자 초기화 불가 (참고)
	@Test
	public void 강의_리스트_조회용_매퍼_테스트() {
		
		//given
		
		//when
		List<ClassDTO> classList = classMapper.selectClassList();
		
		//then
		assertNotNull(classList);
	}
	
	
	/*
	 * @Test public void 강의_상세보기_조회용_매퍼_테스트() {
	 * 
	 * //given
	 * 
	 * //when List<ClassDTO> classList = classMapper.selectAllClass();
	 * 
	 * //then assertNotNull(classList);
	 * 
	 * }
	 */
	
	@Test
	@DisplayName("강의가 잘 등록되는지 매퍼 인터페이스 메소드 확인")
	public void registClass() {
		
		//given
		ClassDTO classDTO = new ClassDTO();
		MemberDTO member = new MemberDTO();
		List<DayDTO> day = new ArrayList<DayDTO>();
		List<TimeDTO> time = new ArrayList<TimeDTO>();
		member.setId("director");
		classDTO.setMember(member); // classDTO에 memberDTO(member)가 담겨야 함
		day.add(new DayDTO(1, null));
		day.add(new DayDTO(3, null));
		time.add(new TimeDTO(1, null));
		time.add(new TimeDTO(2, null));// 3교시
		classDTO.setDayList(day); // classDTO에 DayDTO 담기
		classDTO.setTime(time); // classDTO에 TimeDTO 담기
		classDTO.setClsName("강의명 테스트");
		classDTO.setClsSubject("자바");
		classDTO.setClsStuNum("20");
		classDTO.setClsGrade("성인");
		classDTO.setClsPay(300000);
		classDTO.setClsRoom("L강의실");
		classDTO.setClsStart("2021.05.02");
		classDTO.setClsEnd("2022.05.30");
		classDTO.setClsNote("비고란 테스트");
		classDTO.setClsStatus("Y");
		
		//when
		int result = classMapper.registClass(classDTO);
		
		//then
		assertEquals(1, result);
	}
	
	
	@Test
	@DisplayName("강의가 잘 삭제되는지 매퍼 인터페이스 메소드 확인")
	public void deleteClass() {
		
		// given
		int clsId = 464; // clsId : 강의PK
				
		// when
		int result = classMapper.deleteClass(clsId);
				
		// then
		assertEquals(1, result);
		
			}
	
	
	@Test
	@DisplayName("강의가 잘 수정되는지 매퍼 인터페이스 메소드 확인")
	public void updateClass() {
		
		// given
		ClassDTO classDTO = new ClassDTO();
		MemberDTO member = new MemberDTO();
		List<DayDTO> day = new ArrayList<DayDTO>();
		List<TimeDTO> time = new ArrayList<TimeDTO>();
		member.setId("director");
		classDTO.setMember(member); // classDTO에 memberDTO(member)가 담겨야 함
		classDTO.setClsId(563);
		day.add(new DayDTO(1, null));
		day.add(new DayDTO(3, null));
		time.add(new TimeDTO(1, null));
		time.add(new TimeDTO(2, null));// 3교시
		classDTO.setDayList(day); // classDTO에 DayDTO 담기
		classDTO.setTime(time); // classDTO에 TimeDTO 담기
		classDTO.setClsName("강의명 테스트");
		classDTO.setClsSubject("자바");
		classDTO.setClsStuNum("20");
		classDTO.setClsGrade("성인");
		classDTO.setClsPay(300000);
		classDTO.setClsRoom("L강의실");
		classDTO.setClsNote("비고란 테스트");
		classDTO.setClsStatus("Y"); 
				
		// when
		int result = classMapper.updateClass(classDTO);
				
		// then
		assertEquals(1, result);
		
			}
	
	
	
	
		
	}
	
