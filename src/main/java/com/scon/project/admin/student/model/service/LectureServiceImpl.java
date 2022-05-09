package com.scon.project.admin.student.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.scon.project.admin.Class.dto.ClassDTO;
import com.scon.project.admin.student.model.dao.LectureMapper;
import com.scon.project.admin.student.model.dto.LectureDTO;

@Service("lectureService")
@Transactional
public class LectureServiceImpl implements LectureService {
	
	private final LectureMapper lectureMapper;
	
	@Autowired
	public LectureServiceImpl(LectureMapper lectureMapper) {
		this.lectureMapper = lectureMapper;
	}

	/* 수강 내역 조회용 */
	@Override
	public List<LectureDTO> selectAllLectureList() throws Exception {
		
		List<LectureDTO> lectureList = lectureMapper.selectAllLectureList();
		
		if(lectureList == null) {
			throw new Exception("수강 내역 조회에 실패하였습니다.");
		}
		
		return lectureList;
	}

	/* 전체 강의 조회용 */
	@Override
	public List<ClassDTO> findAllClassList() throws Exception {
		
		List<ClassDTO> classList = lectureMapper.findAllClassList();
		
		if(classList == null) {
			throw new Exception("전체 강의 조회에 실패하였습니다.");
		}
		
		return classList;
	}

	/* 수강 등록용 */
	@Override
	public int insertLecture(LectureDTO lec) throws Exception {
		
		int result = lectureMapper.insertLecture(lec);
		
		if(result <= 0) {
			throw new Exception("수강 등록에 실패하였습니다.");
		}
		
		return result;
	}

}
