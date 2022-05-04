package com.scon.project.admin.Class.dao;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.scon.project.admin.Class.dto.ClassDTO;
import com.scon.project.config.SconApplication;

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
		List<ClassDTO> classList = classMapper.selectClass();
		
		//then
		assertNotNull(classList);
	}
	
	
	@Test
	public void 강의_상세보기_조회용_매퍼_테스트() {
		
		//given
		
		//when
		List<ClassDTO> classList = classMapper.selectAllClass();
				
		//then
		assertNotNull(classList);
		
	}
	
}
