package com.scon.project.admin.consultant.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.scon.project.admin.consultant.model.dao.ConsultantMapper;
import com.scon.project.admin.consultant.model.dto.ConsultantDTO;
import com.scon.project.common.paging.Criteria;

@Service("consultantService")
@Transactional
public class ConsultantServiceImpl implements ConsultantService {

	private final ConsultantMapper consultantMapper;
	
	@Autowired
	public ConsultantServiceImpl(ConsultantMapper consultantMapper) {
		this.consultantMapper = consultantMapper;
	}
	
	/* 상담 신청 내역 조회용 */
//	@Override
//	public List<ConsultantDTO> selectAllConsultantHopeList() throws Exception {
//		
//		List<ConsultantDTO> consultantHopeList = consultantMapper.selectAllConsultantHopeList();
//		
//		if(consultantHopeList == null) {
//			throw new Exception("상담 신청 내역 조회에 실패하였습니다.");
//		}
//		
//		return consultantHopeList;
//	}

	/* 상담 신청 내역 조회용(페이징) */
	@Override
	public List<ConsultantDTO> selectAllConsultantHopeList(Criteria cri) throws Exception {
		
		List<ConsultantDTO> consultantHopeList = consultantMapper.selectAllConsultantHopeList(cri);
		
		if(consultantHopeList == null) {
			throw new Exception("상담 신청 내역 조회에 실패하였습니다.");
		}
		
		return consultantHopeList;
	}

	/* 게시물 총 갯수(페이징) - 상담 신청 */
	@Override
	public int totalHope(Criteria cri) {

		int result = consultantMapper.totalHope(cri);
		
		return result;
	}

	/* 상담 신청 상세 조회용 */
	@Override
	public ConsultantDTO selectConsultantHopeDetail(int no) throws Exception {
		
		ConsultantDTO consultantHopeDetail = consultantMapper.selectConsultantHopeDetail(no);
		
		if(consultantHopeDetail == null) {
			throw new Exception("상담 신청 상세 조회에 실패하였습니다.");
		}
		
		return consultantHopeDetail;
	}
	
	/* 상담 일지 내역 조회용 */
//	@Override
//	public List<ConsultantDTO> selectAllConsultantList() throws Exception {
//		
//		List<ConsultantDTO> consultantList = consultantMapper.selectAllConsultantList();
//		
//		if(consultantList == null) {
//			throw new Exception("상담 일지 내역 조회에 실패하였습니다.");
//		}
//		
//		return consultantList;
//	}
	
	/* 상담 일지 내역 조회용(페이징) */
	@Override
	public List<ConsultantDTO> selectAllConsultantList(Criteria cri) throws Exception {
		
		List<ConsultantDTO> consultantList = consultantMapper.selectAllConsultantList(cri);
		
		if(consultantList == null) {
			throw new Exception("상담 일지 내역 조회에 실패하였습니다.");
		}
		
		return consultantList;
	}

	/* 상담 일지 등록용 */
	@Override
	public int insertConsultant(ConsultantDTO con) throws Exception {
		
		int result = consultantMapper.insertConsultant(con);
		
		if(result <= 0) {
			throw new Exception("상담 일지 등록에 실패하였습니다.");
		}
		
		return result;
	}

	/* 게시물 총 갯수(페이징) - 상담 일지 */
	@Override
	public int total(Criteria cri) {
		
		int result = consultantMapper.total(cri);
		
		return result;
	}

	/* 상담 일지 상세 조회용 */
	@Override
	public ConsultantDTO selectConsultantDetail(int no) throws Exception {
		
		ConsultantDTO consultantDetail = consultantMapper.selectConsultantDetail(no);
		
		if(consultantDetail == null) {
			throw new Exception("상담 일지 상세 조회에 실패하였습니다.");
		}
		
		return consultantDetail;
	}

	/* 상담 일지 수정용 */
	@Override
	public int modifyConsultant(ConsultantDTO con) throws Exception {
		
		int result = consultantMapper.modifyConsultant(con);
		
		if(result <= 0) {
			throw new Exception("상담 일지 수정에 실패하였습니다.");
		}
		
		return result;
	}

	/* 상담 일지 삭제용 */
	@Override
	public int deleteConsultant(int no) throws Exception {
		
		int result = consultantMapper.deleteConsultant(no);
		
		if(result <= 0) {
			throw new Exception("상담 일지 삭제에 실패하였습니다.");
		}
		
		return result;
	}

}
