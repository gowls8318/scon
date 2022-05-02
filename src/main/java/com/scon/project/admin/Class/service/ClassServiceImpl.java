package com.scon.project.admin.Class.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.scon.project.admin.Class.dao.ClassMapper;
import com.scon.project.admin.Class.dto.ClassDTO;

@Service("classService")
@Transactional
public class ClassServiceImpl implements ClassService{
	
	private final ClassMapper classMapper;
	
	@Autowired
	public ClassServiceImpl (ClassMapper classMapper) {
		this.classMapper = classMapper;
	}

	@Override
	public boolean registMenu(ClassDTO classDTO) throws Exception {
		
		int result = classMapper.registClass(classDTO);
		
		if (result <= 0) {
			throw new Exception("강의 등록에 실패했습니다.");
		}
		
		return result > 0 ? true : false;
	}
	

	

}
