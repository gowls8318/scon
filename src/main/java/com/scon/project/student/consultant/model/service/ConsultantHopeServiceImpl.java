package com.scon.project.student.consultant.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.scon.project.admin.consultant.model.dto.ConsultantDTO;
import com.scon.project.student.consultant.model.dao.ConsultantHopeMapper;

@Service("consultantHopeService")
@Transactional
public class ConsultantHopeServiceImpl implements ConsultantHopeService {

	private final ConsultantHopeMapper consultantHopeMapper;
	
	@Autowired
	public ConsultantHopeServiceImpl(ConsultantHopeMapper consultantHopeMapper) {
		this.consultantHopeMapper = consultantHopeMapper;
	}
	
	/* 상담 신청 내역 조회용 */
	@Override
	public List<ConsultantDTO> selectAllConsultantList() {
		
		return consultantHopeMapper.selectAllConsultantList();
	}

	/* 상담 신청 등록용 */
	@Override
	public boolean insertConsultant(ConsultantDTO con) throws Exception {
		
		int result = consultantHopeMapper.insertConsultant(con);
		
		if(result <= 0) {
			throw new Exception("상담 신청 등록에 실패하였습니다.");
		}
		
		return result > 0 ? true : false;
	}

	/* 상담 신청 상세 조회용 */
	@Override
	public ConsultantDTO selectConsultantDetail(int conNo) {
		
		return consultantHopeMapper.selectConsultantDetail(conNo);
	}

	/* 상담 신청 수정용 */
	@Override
	public int modifyConsultant(ConsultantDTO con) {
		
		return consultantHopeMapper.modifyConsultant(con);
	}

	/* 상담 신청 삭제용 */
	@Override
	public int deleteConsultant(int no) {
		
		return consultantHopeMapper.deleteConsultant(no);
	}

}
