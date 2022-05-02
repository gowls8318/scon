package com.scon.project.model.dao.classDao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.scon.project.admin.Class.dao.ClassMapper;
import com.scon.project.admin.Class.dto.ClassDTO;
import com.scon.project.config.SconApplication;

@SpringBootTest
@ContextConfiguration(classes = {SconApplication.class})
public class classMapperTest {
	
	@Autowired
	private ClassMapper classMapper;

	@Test
	@DisplayName("강의 등록이 잘 되는지 매퍼 인터페이스 메소드 확인")
	public void testRegistClass() {
		
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
		int result = classMapper.registClass(classDTO);
		
		//then
		assertEquals(1, result); 
		
		
		
	}
	
}
