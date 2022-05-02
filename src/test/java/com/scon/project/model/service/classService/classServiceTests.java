package com.scon.project.model.service.classService;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.scon.project.admin.Class.dto.ClassDTO;
import com.scon.project.admin.Class.service.ClassService;
import com.scon.project.config.SconApplication;

@SpringBootTest
@ContextConfiguration(classes = {SconApplication.class})
public class classServiceTests {
	
	//의존성주입
	@Autowired
	private ClassService classService; 
	
	
	//등록성공테스트
	@Test
	public void 강의_등록용_서비스_성공_테스트() throws Exception {
		
		//given
		ClassDTO classDTO = new ClassDTO();
		classDTO.setClsName("강의명 테스트");
		classDTO.setClsStuNum("20");
		classDTO.setClsGrade("성인");
		classDTO.setClsPay(300000);
		classDTO.setClsRoom("L강의실");
		classDTO.setClsStart("2021.05.02");
		classDTO.setClsEnd("2022.05.30");
		classDTO.setClsNote("비고란 테스트");
		classDTO.setClsStatus("Y");
		
		//when 
		boolean result = classService.registMenu(classDTO);
		
		//then
		assertTrue(result);
		
	}
	
	//등록실패테스트
	@Test
	public void 강의_등록용_서비스_실패_테스트() {
		
		//given
		ClassDTO classDTO = new ClassDTO();
		classDTO.setClsName("강의명 테스트");
		classDTO.setClsStuNum("20");
		classDTO.setClsGrade("성인");
		classDTO.setClsPay(-10);
		classDTO.setClsRoom("L강의실");
		classDTO.setClsStart("2021.05.02");
		classDTO.setClsEnd("2022.05.30");
		classDTO.setClsNote("비고란 테스트");
		classDTO.setClsStatus("Y");
		
		//when & then
		assertThrows(Exception.class, () -> classService.registMenu(classDTO)); 
		
	}
	
	

}
