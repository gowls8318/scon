package com.scon.project.student.consultant.model.service;

import java.util.List;

import com.scon.project.admin.consultant.model.dto.ConsultantDTO;
import com.scon.project.common.paging.Criteria;

public interface ConsultantHopeService {
	
	/* 상담 신청 내역 조회용 */
//	List<ConsultantDTO> selectAllConsultantList() throws Exception;
	List<ConsultantDTO> selectAllConsultantList(Criteria cri) throws Exception;
	
	/* 게시물 총 갯수 */
	int total(Criteria cri) throws Exception;
	
	/* 상담 신청 등록용 */
	boolean insertConsultant(ConsultantDTO con) throws Exception;

	/* 상담 신청 상세 조회용 */
	ConsultantDTO selectConsultantDetail(int no) throws Exception;

	/* 상담 신청 수정용 */
	int modifyConsultant(ConsultantDTO con) throws Exception;
	
	/* 상담 신청 삭제용 */
	int deleteConsultant(int no) throws Exception;

}
