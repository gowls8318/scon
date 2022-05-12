package com.scon.project.admin.business.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.scon.project.admin.business.model.dao.BusinessMapper;
import com.scon.project.admin.business.model.dto.BusinessDTO;

@Service("businessService")
@Transactional
public class BusinessServiceImpl implements BusinessService {
	
	private final BusinessMapper businessMapper;
	
	@Autowired
	public BusinessServiceImpl(BusinessMapper businessMapper) {
		this.businessMapper = businessMapper;
	}
	//조회
	@Override
	public BusinessDTO selectBusinessInfo() {
		
		return businessMapper.selectBusinessInfo();
	}
	
//	@Override
//	public BusinessDTO selectBusinessInfo(int busCode) {
//		BusinessDTO BusinessDTO = businessMapper.selectBusinessInfo(busCode);
//		return businessMapper.selectBusinessInfo(busCode);
//	}
	
	//수정
	@Override
	public void insertBusinessInfo(BusinessDTO BusinessDTO) {
		
		businessMapper.insertBusinessInfo(BusinessDTO);
	}
	
}