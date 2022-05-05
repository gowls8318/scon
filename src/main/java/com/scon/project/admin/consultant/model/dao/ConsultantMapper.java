package com.scon.project.admin.consultant.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.scon.project.admin.consultant.model.dto.ConsultantDTO;

@Mapper
public interface ConsultantMapper {

	/* 상담 신청 내역 조회용 */
	List<ConsultantDTO> selectAllConsultantHopeList();

	/* 상담 신청 상세 조회용 */
	ConsultantDTO selectConsultantHopeDetail(int no);

	/* 상담 일지 내역 조회용 */
	List<ConsultantDTO> selectAllConsultantList();
	
	/* 상담 일지 등록용 */
	int insertConsultant(ConsultantDTO con);

}
