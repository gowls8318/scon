package com.scon.project.admin.Class.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.scon.project.admin.Class.dao.ClassMapper;
import com.scon.project.admin.Class.dto.ClassDTO;
import com.scon.project.admin.Class.dto.DayDTO;
import com.scon.project.admin.Class.dto.TimeDTO;
import com.scon.project.member.model.dto.MemberDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("classService")
@Transactional
public class ClassServiceImpl implements ClassService {

	private final ClassMapper classMapper;

	@Autowired
	public ClassServiceImpl(ClassMapper classMapper) {
		this.classMapper = classMapper;
	}

	@Override
	public boolean registClass(ClassDTO classDTO) throws Exception {

		log.info("daylist : {}", classDTO.getDayList());
		log.info("timelist : {}", classDTO.getTime());
		int result = classMapper.registClass(classDTO);

		int result2 = 0;
		for (DayDTO day : classDTO.getDayList()) {
			if (day.getClsDayId() != 0)
				result2 += classMapper.insertClsAndDay(day);
		}
		
		int result3 = 0;
		for (TimeDTO time : classDTO.getTime()) {
			if (time.getClsTimeId() != 0)
				result3 += classMapper.insertClsAndTime(time);
		}

		if (result <= 0 || result2 <= 0 || result3 <= 0) {
			throw new Exception("강의 등록에 실패했습니다.");
		}

		return result > 0 ? true : false;
	}

	//강의리스트조회
	@Override
	public List<ClassDTO> selectClassList() {
		return classMapper.selectClassList();
	}

	@Override
	public List<MemberDTO> findAllmemberList(int clsId) {
		return classMapper.findAllmemberList(clsId);
	}
	
	//강의상세조회
//	@Override
//	public List<ClassDTO> selectAllClass() {
//		
//		return classMapper.selectAllClass();
//	}


}
