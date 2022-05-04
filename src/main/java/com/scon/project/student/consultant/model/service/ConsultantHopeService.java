package com.scon.project.student.consultant.model.service;

import java.util.List;

import com.scon.project.admin.consultant.model.dto.ConsultantDTO;

public interface ConsultantHopeService {
	
	/* 상담 신청 내역 조회용 */
	List<ConsultantDTO> selectAllConsultantList();

	/* 상담 신청 등록용 */
	boolean insertConsultant(ConsultantDTO con) throws Exception;

	/* 상담 신청 상세 조회용 */
	ConsultantDTO selectConsultantDetail(int conNo);

	/* 상담 신청 수정용 */
	int modifyConsultant(ConsultantDTO con);

}
