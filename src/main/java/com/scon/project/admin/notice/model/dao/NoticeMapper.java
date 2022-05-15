package com.scon.project.admin.notice.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.scon.project.admin.notice.model.dto.NoticeDTO;

@Mapper
public interface NoticeMapper {
	
//	공지사항 목록 조회
	List<NoticeDTO> findAllNoticeList();
	
	

//	공지사항 게시글 등록
	int registNotice(NoticeDTO notice) throws Exception;
	
//	게시글 상세 조회
	NoticeDTO sellectNoticeDetail(int no) throws Exception;

	void updateHitCount(int no) throws Exception;


//  게시글 수정	하고 등록
	int modifyNotice(NoticeDTO notice) throws Exception;

//	게시글 삭제
	int deleteNotice(int no) throws Exception;

	


	
	
	
	
	
	
	
	
}
	

	
//	페이징
//	public List<NoticeDTO> findAllNoticeList(Criteria criteria);
//	
//	public int NoticeTotalCount(Criteria criteria);
//}
