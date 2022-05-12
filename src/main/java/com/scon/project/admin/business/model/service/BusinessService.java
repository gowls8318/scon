package com.scon.project.admin.business.model.service;

import com.scon.project.admin.business.model.dto.BusinessDTO;

public interface BusinessService {
	
	//교육원 정보 조회용
	BusinessDTO selectBusinessInfo();
	//BusinessDTO selectBusinessInfo(int busCode);
	
	
	//교육원 정보 입력용
	void insertBusinessInfo(BusinessDTO business);
	
	//교육원 정보 수정용
	//BusinessDTO updateBusinessInfo(ModelAndView mv);



}
