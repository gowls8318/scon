package com.scon.project.admin.consultant.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.scon.project.admin.consultant.model.dao.ConsultantMapper;
import com.scon.project.admin.consultant.model.dto.ConsultantDTO;

@Service("consultantService")
@Transactional
public class ConsultantServiceImpl implements ConsultantService {

	private final ConsultantMapper consultantMapper;
	
	@Autowired
	public ConsultantServiceImpl(ConsultantMapper consultantMapper) {
		this.consultantMapper = consultantMapper;
	}
	
	/* 상담 신청 내역 조회용 */
	@Override
	public List<ConsultantDTO> selectAllConsultantHopeList() {
		
		return consultantMapper.selectAllConsultantHopeList();
	}

	/* 상담 신청 상세 조회용 */
	@Override
	public ConsultantDTO selectConsultantHopeDetail(int no) {
		
		return consultantMapper.selectConsultantHopeDetail(no);
	}
	
	/* 상담 일지 내역 조회용 */
	@Override
	public List<ConsultantDTO> selectAllConsultantList() {
		
		return consultantMapper.selectAllConsultantList();
	}

	/* 상담 일지 등록용 */
	@Override
	public int insertConsultant(ConsultantDTO con) {
		
		return consultantMapper.insertConsultant(con);
	}

}
