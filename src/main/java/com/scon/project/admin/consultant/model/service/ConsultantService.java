package com.scon.project.admin.consultant.model.service;

import java.util.List;

import com.scon.project.admin.consultant.model.dto.ConsultantDTO;
import com.scon.project.common.paging.Criteria;

public interface ConsultantService {

	/* 상담 신청 내역 조회용 */
//	List<ConsultantDTO> selectAllConsultantHopeList() throws Exception;

	/* 상담 신청 내역 조회용(페이징) */
	List<ConsultantDTO> selectAllConsultantHopeList(Criteria cri) throws Exception;
	
	/* 게시물 총 갯수(페이징) - 상담 신청 */
	int totalHope(Criteria cri);
	
	/* 상담 신청 상세 조회용 */
	ConsultantDTO selectConsultantHopeDetail(int no) throws Exception;

	/* 상담 일지 내역 조회용 */
//	List<ConsultantDTO> selectAllConsultantList() throws Exception;
	
	/* 상담 일지 내역 조회용(페이징) */
	List<ConsultantDTO> selectAllConsultantList(Criteria cri) throws Exception;
	
	/* 게시물 총 갯수(페이징) - 상담 일지 */
	int total(Criteria cri);
	
	/* 상담 일지 등록용 */
	int insertConsultant(ConsultantDTO con) throws Exception;

	/* 상담 일지 상세 조회용 */
	ConsultantDTO selectConsultantDetail(int no) throws Exception;

	/* 상담 일지 수정용 */
	int modifyConsultant(ConsultantDTO con) throws Exception;

	/* 상담 일지 삭제용 */
	int deleteConsultant(int no) throws Exception;

}
