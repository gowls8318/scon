package com.scon.project.student.consultant.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.scon.project.admin.consultant.model.dto.ConsultantDTO;
import com.scon.project.common.paging.Criteria;

@Mapper
public interface ConsultantHopeMapper {

	/* 상담 신청 내역 조회용 */
//	List<ConsultantDTO> selectAllConsultantList();
	
	/* 상담 신청 내역 조회용(페이징) */
	List<ConsultantDTO> selectAllConsultantList(Criteria cri);
	
	/* 게시물 총 갯수(페이징) */
	int total(Criteria cri);
	
	/* 상담 신청 등록용 */
	int insertConsultant(ConsultantDTO con);

	/* 상담 신청 상세 조회용 */
	ConsultantDTO selectConsultantDetail(int no);

	/* 상담 신청 수정용 */
	int modifyConsultant(ConsultantDTO con);

	/* 상담 신청 삭제용 */
	int deleteConsultant(int no);

}
