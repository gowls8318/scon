package com.scon.project.student.consultant.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.scon.project.admin.consultant.model.dto.ConsultantDTO;
import com.scon.project.common.paging.Criteria;
import com.scon.project.student.consultant.model.dao.ConsultantHopeMapper;

@Service("consultantHopeService")
@Transactional
public class ConsultantHopeServiceImpl implements ConsultantHopeService {

	/* 예외 처리 해주기 */
	
	private final ConsultantHopeMapper consultantHopeMapper;
	
	@Autowired
	public ConsultantHopeServiceImpl(ConsultantHopeMapper consultantHopeMapper) {
		this.consultantHopeMapper = consultantHopeMapper;
	}
	
	/* 상담 신청 내역 조회용 */	
//	@Override
//	public List<ConsultantDTO> selectAllConsultantList() throws Exception {
//		
//		List<ConsultantDTO> consultantList = consultantHopeMapper.selectAllConsultantList();
//		
//		if(consultantList == null) {
//			throw new Exception("상담 신청 내역 조회에 실패하였습니다.");
//		}
//		
//		return consultantList;
//	}

	@Override
	public List<ConsultantDTO> selectAllConsultantList(Criteria cri) {
		
		return consultantHopeMapper.selectAllConsultantList(cri);
	}
	
	/* 게시물 총 갯수 */
	@Override
	public int total(Criteria cri) {
		
		return consultantHopeMapper.total(cri);
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
	public ConsultantDTO selectConsultantDetail(int no) throws Exception {
		
		ConsultantDTO consultant = consultantHopeMapper.selectConsultantDetail(no);
		
		if(consultant == null) {
			throw new Exception("상담 신청 상세 조회에 실패하였습니다.");
		}
		
		return consultant;
	}

	/* 상담 신청 수정용 */
	@Override
	public int modifyConsultant(ConsultantDTO con) throws Exception {
		
		int result = consultantHopeMapper.modifyConsultant(con);
		
		if(result <= 0) {
			throw new Exception("상담 내용 수정 실패하였습니다.");
		}
		
		return result;
	}

	/* 상담 신청 삭제용 */
	@Override
	public int deleteConsultant(int no) throws Exception {
		
		int result = consultantHopeMapper.deleteConsultant(no);
		
		if(result <= 0) {
			throw new Exception("상담 삭제에 실패하였습니다.");
		}
		
		return result;
	}

}
