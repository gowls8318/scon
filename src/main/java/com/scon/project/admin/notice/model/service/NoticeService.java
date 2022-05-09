package com.scon.project.admin.notice.model.service;

import java.util.List;

import com.scon.project.admin.notice.model.dto.NoticeDTO;

public interface NoticeService {

//	공지 목록 조회
	List<NoticeDTO> findAllNoticeList();

//	공지 게시글 등록
	int registNotice(NoticeDTO noti);
	
//	게시글 상세 조회
	NoticeDTO sellectNoticeDetail(int idx) throws Exception;

	
}
