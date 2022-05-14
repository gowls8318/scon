package com.scon.project.admin.schedule.model.service;

import java.util.List;

import com.scon.project.admin.schedule.model.dto.ScheduleDTO;

public interface ScheduleService {

	List<ScheduleDTO> findSchedule();

	int registSchedule(ScheduleDTO sche);

	int updateSchedule(ScheduleDTO sche);

	int deleteSchedule(ScheduleDTO sche);

}
