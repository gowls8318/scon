package com.scon.project.admin.schedule.model.service;

import java.util.List;

import com.scon.project.admin.schedule.model.dto.ScheduleDTO;
import com.scon.project.member.model.dto.UserImpl;

public interface ScheduleService {

	List<ScheduleDTO> findSchedule(UserImpl user);

	int registSchedule(ScheduleDTO sche);

	int updateSchedule(ScheduleDTO sche);

	int deleteSchedule(ScheduleDTO sche);

}
