package com.scon.project.admin.schedule.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.scon.project.admin.schedule.model.dao.ScheduleMapper;
import com.scon.project.admin.schedule.model.dto.ScheduleDTO;
import com.scon.project.member.model.dto.UserImpl;

@Service("ScheduleService")
@Transactional
public class ScheduleServiceImpl implements ScheduleService{
	
	private final ScheduleMapper scheduleMapper;
	
	@Autowired
	public ScheduleServiceImpl(ScheduleMapper scheduleMapper) {
		this.scheduleMapper = scheduleMapper;
	}

	@Override
	public List<ScheduleDTO> findSchedule(UserImpl user) {
		return scheduleMapper.findSchedule(user);
	}

	@Override
	public int registSchedule(ScheduleDTO sche) {
		return scheduleMapper.registSchedule(sche);
	}

	@Override
	public int updateSchedule(ScheduleDTO sche) {
		return scheduleMapper.updateSchedule(sche);
	}

	@Override
	public int deleteSchedule(ScheduleDTO sche) {
	
		return scheduleMapper.deleteSchedule(sche);
	}

	

}
