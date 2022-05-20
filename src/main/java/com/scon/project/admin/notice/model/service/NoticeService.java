package com.scon.project.admin.notice.model.service;

import java.util.List;

import com.scon.project.admin.notice.model.dto.NoticeCmtDTO;
import com.scon.project.admin.notice.model.dto.NoticeDTO;
import com.scon.project.common.paging.Criteria;

public interface NoticeService {

//	공지 목록 조회
	List<NoticeDTO> findAllNoticeList(Criteria cri) throws Exception;	
	
//	글 전체 갯수
	int total(Criteria cri);
	
	
//	공지 게시글 등록
	int registNotice(NoticeDTO notice) throws Exception;

//	게시글 상세 조회
	NoticeDTO selectNoticeDetail(int no) throws Exception;
	
//  게시글 수정 하고 등록	
	int modifyNotice(NoticeDTO notice) throws Exception;
	
//	게시글 삭제
	int deleteNotice(int no) throws Exception;
	
	
//	댓글조회
	List<NoticeCmtDTO> readCmt(int cNo) throws Exception;
	
//	댓글 등록
	int registCmt(NoticeCmtDTO notiCmt) throws Exception;
	
//	댓글 삭제
	int deleteCmt(int cNo) throws Exception;
	
//	댓글 수정+등록
	int modifyCmt(NoticeCmtDTO notiCmt) throws Exception;

	



	

	


	
	
	
	
	
	
	
	
	
	

	
}
