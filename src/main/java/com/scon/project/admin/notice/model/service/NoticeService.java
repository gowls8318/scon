package com.scon.project.admin.notice.model.service;

import java.util.List;

import com.scon.project.admin.notice.model.dto.NoticeDTO;
import com.scon.project.admin.notice.paging.Criteria;

public interface NoticeService {

//	공지 목록 조회
//	List<NoticeDTO> findAllNoticeList() throws Exception;
	
//	목록+페이징
	List<NoticeDTO> findAllNoticeList(Criteria cri) throws Exception;
	
//	게시물 총 갯수
//	int allNoticeCount() throws Exception;
	

	
	
	
	
	
	
	
//	공지 게시글 등록
	int registNotice(NoticeDTO noti);
	
//	게시글 상세 조회
	NoticeDTO selectNoticeDetail(int no) throws Exception;
	
//  게시글 수정
	void updateNoticeDetail(NoticeDTO noti);
	

	
}
