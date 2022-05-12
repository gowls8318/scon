package com.scon.project.admin.business.model.dao;


import org.apache.ibatis.annotations.Mapper;

import com.scon.project.admin.business.model.dto.BusinessDTO;

@Mapper
public interface BusinessMapper {
	
	//교육원 정보 조회
	BusinessDTO selectBusinessInfo();
	//BusinessDTO selectBusinessInfo(int busCode);
	
	//교육원 정보 입력
	void insertBusinessInfo(BusinessDTO businessDTO);


}
