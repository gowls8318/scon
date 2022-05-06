package com.scon.project.admin.notice.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.scon.project.admin.notice.model.dto.NoticeDTO;

@Mapper
public interface NoticeMapper {
	
//	공지사항 목록 조회
	List<NoticeDTO> findAllNoticeList();

//	공지사항 게시글 등록
	int registNotice(NoticeDTO noti);
	
//	게시글 상세 조회
	NoticeDTO sellectNoticeDetail();

}
