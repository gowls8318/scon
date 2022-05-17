package com.scon.project.admin.notice.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.scon.project.admin.notice.model.dao.NoticeMapper;
import com.scon.project.admin.notice.model.dto.NoticeCmtDTO;
import com.scon.project.admin.notice.model.dto.NoticeDTO;
import com.scon.project.common.paging.Criteria;

@Service("noticeService")
@Transactional
public class NoticeServiceImpl implements NoticeService {
	
	private final NoticeMapper noticeMapper;
	
	@Autowired
	public NoticeServiceImpl(NoticeMapper noticeMapper) {
		this.noticeMapper = noticeMapper;
	}

//	목록조회 + 페이징
	@Override
	public List<NoticeDTO> findAllNoticeList(Criteria cri) throws Exception {
		
		return noticeMapper.findAllNoticeList(cri);
	}	
//	글 전체 갯수
	@Override
	public int total(Criteria cri) {
		return noticeMapper.total(cri);
	}
	
		
// 게시글 등록
	@Override
	public int registNotice(NoticeDTO notice) throws Exception {
		return noticeMapper.registNotice(notice);
	}

//	게시글 상세 조회
	@Override
	public NoticeDTO selectNoticeDetail(int no) throws Exception {
//		선택한 게시글 조회수 증가
		noticeMapper.updateHitCount(no);
		
//		선택한 게시글의 내용 조회
		NoticeDTO noticeDetail = noticeMapper.sellectNoticeDetail(no);
		
		return noticeDetail;
	}
//	댓글 조회
	@Override
	public List<NoticeCmtDTO> readCmt(int no) throws Exception {
		return noticeMapper.readCmt(no);
	}


	
//	댓글 등록
	@Override
	public int registCmt(NoticeCmtDTO notiCmt) throws Exception {
		
		return noticeMapper.registCmt(notiCmt);
	}
	

//  게시글 수정하고 등록		
	@Override
	public int modifyNotice(NoticeDTO notice) throws Exception {
				
		return noticeMapper.modifyNotice(notice);
	}

//	게시글 삭제
	@Override
	public int deleteNotice(int no) throws Exception {
		
		return noticeMapper.deleteNotice(no);
		
	}









}
