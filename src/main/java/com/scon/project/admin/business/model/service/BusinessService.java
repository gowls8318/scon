package com.scon.project.admin.business.model.service;

import com.scon.project.admin.business.model.dto.BusinessDTO;

public interface BusinessService {
	
	//교육원 정보 조회용
	BusinessDTO selectBusinessInfo();

	//교육원 정보 입력용
	int insertBusinessInfo(BusinessDTO bus);


}
