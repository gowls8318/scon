package com.scon.project.admin.consultant.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.scon.project.admin.consultant.model.dto.ConsultantDTO;

@Mapper
public interface ConsultantMapper {

	/* 상담 신청 내역 조회용 */
	List<ConsultantDTO> selectAllConsultantHopeList();

}
