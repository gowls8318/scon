package com.scon.project.admin.schedule.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.scon.project.admin.schedule.model.dto.ScheduleDTO;

@Mapper
public interface ScheduleMapper {

	List<ScheduleDTO> findSchedule();

	int registSchedule(ScheduleDTO schedule);

	

}
