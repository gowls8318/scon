package com.scon.project.admin.schedule.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.scon.project.admin.schedule.model.dto.ScheduleDTO;




@Service("ScheduleService")
@Transactional
public class ScheduleServiceImpl implements ScheduleService{

	@Override
	public List<ScheduleDTO> findAllSchedule() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean registSchedule(ScheduleDTO schedule) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}
	
	

}
