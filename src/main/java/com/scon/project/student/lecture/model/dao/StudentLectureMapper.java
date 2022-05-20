package com.scon.project.student.lecture.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.scon.project.admin.lecture.model.dto.LectureDTO;
import com.scon.project.common.paging.Criteria;

@Mapper
public interface StudentLectureMapper {

	/* 수강 내역 조회용 */
//	List<LectureDTO> selectAllLectureList();

	/* 수강 내역 조회용(페이징) */
	List<LectureDTO> selectAllLectureList(Criteria cri);

	/* 게시물 총 갯수(페이징) */
	int total(Criteria cri);

	/* 수강 정보 조회용 */
	LectureDTO selectLectureDetail(int no);
	
}
