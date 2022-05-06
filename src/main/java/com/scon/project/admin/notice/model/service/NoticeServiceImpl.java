package com.scon.project.admin.notice.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.scon.project.admin.notice.model.dao.NoticeMapper;
import com.scon.project.admin.notice.model.dto.NoticeDTO;

@Service("noticeService")
@Transactional
public class NoticeServiceImpl implements NoticeService {
	
	private final NoticeMapper noticeMapper;
	
	@Autowired
	public NoticeServiceImpl(NoticeMapper noticeMapper) {
		this.noticeMapper = noticeMapper;
	}

//	목록조회
	@Override
	public List<NoticeDTO> findAllNoticeList() {
		return noticeMapper.findAllNoticeList();
	}
	
// 게시글 등록
	@Override
	public int registNotice(NoticeDTO noti) {
		return noticeMapper.registNotice(noti);
	}

//	게시글 상세 조회
	@Override
	public NoticeDTO sellectNoticeDetail() {
		return noticeMapper.sellectNoticeDetail();
	}
	
//	@Override
//	public boolean registNotice(NoticeDTO noti) throws Exception {
//		int result = noticeMapper.registNotice(noti);
//		
//		if(result <= 0) {
//			throw new Exception("게시글 등록에 실패하였습니다.");
//		}
//		
//		return result > 0 ? true : false;
//	}


}
