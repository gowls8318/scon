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

	@Override
	public List<NoticeDTO> findAllNoticeList() {
		return noticeMapper.findAllNoticeList();
	}


}
