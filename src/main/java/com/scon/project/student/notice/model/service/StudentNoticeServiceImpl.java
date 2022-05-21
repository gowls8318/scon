package com.scon.project.student.notice.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.scon.project.admin.notice.model.dto.NoticeCmtDTO;
import com.scon.project.admin.notice.model.dto.NoticeDTO;
import com.scon.project.common.paging.Criteria;
import com.scon.project.student.notice.model.dao.StudentNoticeMapper;

@Service("StudentNoticeService")
@Transactional
public class StudentNoticeServiceImpl implements StudentNoticeService {
	
	private final StudentNoticeMapper studentNoticeMapper;

	@Autowired
	public StudentNoticeServiceImpl(StudentNoticeMapper studentNoticeMapper) {
		this.studentNoticeMapper = studentNoticeMapper;
	}

//	목록조회 + 페이징
	@Override
	public List<NoticeDTO> findAllNoticeList(Criteria cri) throws Exception {
		
		return studentNoticeMapper.findAllNoticeList(cri);
	}	
//	글 전체 갯수
	@Override
	public int total(Criteria cri) {
		return studentNoticeMapper.total(cri);
	}
	
 
// 게시글 상세 조회
	  
	  @Override public NoticeDTO selectNoticeDetail(int no) throws Exception { //
//선택한 게시글 조회수 증가 
		  studentNoticeMapper.updateHitCount(no);
	  
// 선택한 게시글의 내용 조회 
		  NoticeDTO noticeDetail = studentNoticeMapper.sellectNoticeDetail(no);
	  
	  return noticeDetail; 
	  }


// 댓글 조회
 
	 @Override public List<NoticeCmtDTO> readCmt(int cNo) throws Exception {
	 return studentNoticeMapper.readCmt(cNo); }
	 // 댓글 등록
	 /* 
	 * @Override public int registCmt(NoticeCmtDTO notiCmt) throws Exception {
	 * 
	 * return noticeMapper.registCmt(notiCmt); }
	 * 
	 * // 댓글 삭제
	 * 
	 * @Override public int deleteCmt(int cNo) throws Exception {
	 * 
	 * return noticeMapper.deleteCmt(cNo); }
	 * 
	 * 
	 * // 댓글 수정하고 등록
	 * 
	 * @Override public int modifyCmt(NoticeCmtDTO notiCmt) throws Exception {
	 * 
	 * return noticeMapper.modifyCmt(notiCmt); }
	 * 
	 * 
	 */



}
