package com.scon.project.student.lecture.model.service;

import java.util.List;

import com.scon.project.admin.lecture.model.dto.LectureDTO;
import com.scon.project.common.paging.Criteria;

public interface StudentLectureService {

	/* 수강 내역 조회용 */
//	List<LectureDTO> selectAllLectureList() throws Exception;

	/* 수강 내역 조회용(페이징) */
	List<LectureDTO> selectAllLectureList(Criteria cri) throws Exception;

	/* 게시물 총 갯수(페이징) */
	int total(Criteria cri);

	/* 수강 정보 조회용 */
	LectureDTO selectLectureDetail(int no) throws Exception;
	
}
