package com.scon.project.admin.lecture.model.service;

import java.util.List;

import com.scon.project.admin.Class.dto.ClassDTO;
import com.scon.project.admin.lecture.model.dto.LectureDTO;
import com.scon.project.admin.lecture.model.dto.RefundDTO;
import com.scon.project.common.paging.Criteria;
import com.scon.project.member.model.dto.MemberDTO;

public interface LectureService {

	/* 수강 내역 조회용 */
//	List<LectureDTO> selectAllLectureList() throws Exception;
	
	/* 수강 내역 조회용(페이징) */
	List<LectureDTO> selectAllLectureList(Criteria cri) throws Exception;
	
	/* 게시물 총 갯수(페이징) */
	int total(Criteria cri);
	
	/* 강의 조회용 */
	List<ClassDTO> selectAllClassList() throws Exception;

	/* 원생 조회용 */
	List<MemberDTO> selectAllMemberList() throws Exception;
	
	/* 수강 등록용 */
	int insertLecture(LectureDTO lec) throws Exception;

	/* 수강 수정용 - 정보 조회 */
	LectureDTO selectLectureDetail(int no) throws Exception;

	/* 수강 수정용 */
	int modifyLecture(LectureDTO lec) throws Exception;

	/* 수강 삭제용 */
	int deleteLecture(int no) throws Exception;

	/* 환불 등록용 */
	int insertRefund(RefundDTO ref) throws Exception;

}
