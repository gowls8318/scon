package com.scon.project.admin.business.model.dao;

import org.apache.ibatis.annotations.Mapper;

import com.scon.project.admin.business.model.dto.BusinessDTO;

@Mapper
public interface BusinessMapper {

	BusinessDTO selectBusinessInfo();
	

}
