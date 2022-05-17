package com.scon.project.admin.schedule.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.scon.project.admin.schedule.model.dto.ScheduleDTO;
import com.scon.project.member.model.dto.UserImpl;

@Mapper
public interface ScheduleMapper {

	List<ScheduleDTO> findSchedule(UserImpl user);

	int registSchedule(ScheduleDTO schedule);

	int updateSchedule(ScheduleDTO sche);

	int deleteSchedule(ScheduleDTO sche);

}
