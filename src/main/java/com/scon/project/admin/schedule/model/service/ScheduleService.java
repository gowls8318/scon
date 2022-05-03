package com.scon.project.admin.schedule.model.service;

import java.util.List;

import com.scon.project.admin.schedule.model.dto.ScheduleDTO;

public interface ScheduleService {

	List<ScheduleDTO> findAllSchedule();

	boolean registSchedule(ScheduleDTO schedule) throws Exception;

}
