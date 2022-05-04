package com.scon.project.admin.notice.model.service;

import java.util.List;

import com.scon.project.admin.notice.model.dto.NoticeDTO;

public interface NoticeService {

	List<NoticeDTO> findAllNoticeList();

}
