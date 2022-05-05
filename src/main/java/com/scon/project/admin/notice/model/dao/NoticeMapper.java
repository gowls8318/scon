package com.scon.project.admin.notice.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.scon.project.admin.notice.model.dto.NoticeDTO;

@Mapper
public interface NoticeMapper {

	List<NoticeDTO> findAllNoticeList();
	
	int registNotice(NoticeDTO menu);

}
