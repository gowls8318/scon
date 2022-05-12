package com.scon.project.admin.notice.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.scon.project.admin.notice.model.dto.NoticeDTO;
import com.scon.project.admin.notice.paging.Criteria;

@Mapper
public interface NoticeMapper {
	
//////	공지사항 목록 조회
//	List<NoticeDTO> findAllNoticeList();
	
//	목록+페이징
	List<NoticeDTO> findAllNoticeList(Criteria cri) throws Exception;

//	게시물 총 갯수
//	int allNoticeCount() throws Exception;
	
	

//	공지사항 게시글 등록
	int registNotice(NoticeDTO noti);
	
//	게시글 상세 조회
	NoticeDTO sellectNoticeDetail(int no) throws Exception;

	void updateHitCount(int no) throws Exception;

//  게시글 수정
	void updateNoticeDetail(NoticeDTO noti);

	
	
	
	
	
	
	
	
}
	

	
//	페이징
//	public List<NoticeDTO> findAllNoticeList(Criteria criteria);
//	
//	public int NoticeTotalCount(Criteria criteria);
//}
