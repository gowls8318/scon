package com.scon.project.admin.Class.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.scon.project.admin.Class.dao.ClassMapper;
import com.scon.project.admin.Class.dto.ClassDTO;
import com.scon.project.admin.Class.dto.DayDTO;
import com.scon.project.admin.Class.dto.TimeDTO;
import com.scon.project.common.paging.Criteria;
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

	// 강의등록
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

	// 강의리스트조회
	@Override
	public List<ClassDTO> selectClassList(Criteria cri) {
		return classMapper.selectClassList(cri);
	}

	//강의상세조회
	@Override
	public ClassDTO classDetail(int clsId) {

		return classMapper.classDetail(clsId);
	}

	//요일리스트조회
	@Override
	public List<DayDTO> selectDayList() {
		return classMapper.selectDayList();
	}
	
	// 멤버가져오기
	/*
	 * @Override public List<MemberDTO> findAllmemberList(int clsId) { return
	 * classMapper.findAllmemberList(clsId); }
	 */

	// 강의삭제
	@Override
	public int deleteClass(int clsId) {

		return classMapper.deleteClass(clsId);
	}

	//강의수정
	@Override
	public int classUpdate(ClassDTO classDTO) throws Exception {
		
		
		log.info("dayList {}:" , classDTO.getDayList());
		log.info("time {} : ", classDTO.getTime());
		int result = classMapper.classUpdate(classDTO);
		
		
		 /*int result2 = classMapper.classUpdate(classDTO.getDayList()); 
		 int result3 = classMapper.classUpdate(classDTO.getTime());*/
		
		
		/*
		 * int result2 = 0; for (DayDTO day : classDTO.getDayList()) { if
		 * (day.getClsDayId() != 0) result2 += classMapper.updateDay(day,
		 * classDTO.getClsId()); //dto에 id }
		 * 
		 * int result3 = 0; for (TimeDTO time : classDTO.getTime()) { if
		 * (time.getClsTimeId() != 0) result3 += classMapper.updateTime(time,
		 * classDTO.getClsId()); }
		 * 
		 * if (result <= 0 || result2 <= 0 || result3 <= 0) { throw new
		 * Exception("강의 등록에 실패했습니다."); }
		 */

		return result;
	}

	/*
	 * //요일
	 * 
	 * @Override public int updateDay(DayDTO dayList) { return
	 * classMapper.updateDay(dayList); }
	 * 
	 * //교시
	 * 
	 * @Override public int updateTime(TimeDTO time) { return
	 * classMapper.updateTime(time); }
	 */


	//강사
	@Override
	public List<MemberDTO> registMember() {
		return classMapper.registMember();
	}

	//강의게시글 총 갯수
	@Override
	public int total(Criteria cri) {
		return classMapper.total(cri);
	}


}
