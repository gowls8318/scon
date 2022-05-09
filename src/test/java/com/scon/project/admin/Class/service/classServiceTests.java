package com.scon.project.admin.Class.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Disabled;
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
@ContextConfiguration(classes = { SconApplication.class })
public class classServiceTests {

	// 의존성주입
	@Autowired
	private ClassService classService;
	
	
	
	// 강의리스트조회성공테스트
	@Test
	public void 강의_조회용_서비스_성공_테스트() {
	
	//given

	
	//when
	List<ClassDTO> classList = classService.selectClassList();
	
	//then
	assertNotNull(classList);
	
	}
	
	/*
	 * // 강의상세조회성공테스트
	 * 
	 * @Test public void 강의상세보기_조회용_서비스_성공_테스트() {
	 * 
	 * //given
	 * 
	 * 
	 * //when List<ClassDTO> classList = classService.selectAllClass();
	 * 
	 * //then assertNotNull(classList);
	 * 
	 * }
	 */

	// 등록성공테스트
	@Test
	public void 강의_등록용_서비스_성공_테스트() throws Exception {

		// given
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

		// when
		boolean result = classService.registClass(classDTO);

		// then
		assertTrue(result);

	}

	 //등록실패테스트 ; 성공
	@Test
	@Disabled
	public void 강의_등록용_서비스_실패_테스트() {
		
			// given
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
		
		//when & then
		assertThrows(Exception.class, () -> classService.registClass(classDTO)); 
		
	}
	
	//삭제테스트 
	@Test
	public void 강의_삭제용_서비스_성공_테스트() {
		
		// given
		int clsId = 464;
		
		// when
		int result = classService.deleteClass(clsId);
		
		// then
		assertEquals(1, result);
	}

}
