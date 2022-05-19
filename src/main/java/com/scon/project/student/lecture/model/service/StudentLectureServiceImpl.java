package com.scon.project.student.lecture.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scon.project.admin.lecture.model.dto.LectureDTO;
import com.scon.project.common.paging.Criteria;
import com.scon.project.student.lecture.model.dao.StudentLectureMapper;

@Service("studentLectureService")
public class StudentLectureServiceImpl implements StudentLectureService {

	private final StudentLectureMapper studentLectureMapper;
	
	@Autowired
	public StudentLectureServiceImpl(StudentLectureMapper studentLectureMapper) {
		this.studentLectureMapper = studentLectureMapper;
	}
	
	/* 수강 내역 조회용 */
//	@Override
//	public List<LectureDTO> selectAllLectureList() throws Exception {
//		
//		List<LectureDTO> lectureList = studentLectureMapper.selectAllLectureList();
//		
//		if(lectureList == null) {
//			throw new Exception("수강 내역 조회에 실패하였습니다.");
//		}
//		
//		return lectureList;
//	}

	/* 수강 내역 조회용(페이징) */
	@Override
	public List<LectureDTO> selectAllLectureList(Criteria cri) throws Exception {
		
		List<LectureDTO> lectureList = studentLectureMapper.selectAllLectureList(cri);
		
		if(lectureList == null) {
			throw new Exception("수강 내역 조회에 실패하였습니다.");
		}
		
		return lectureList;
	}

	/* 게시물 총 갯수(페이징) */
	@Override
	public int total(Criteria cri) {
		
		int result = studentLectureMapper.total(cri);
		
		return result;
	}
	
}
