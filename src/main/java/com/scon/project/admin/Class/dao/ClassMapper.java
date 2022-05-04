package com.scon.project.admin.Class.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.scon.project.admin.Class.dto.ClassDTO;
import com.scon.project.admin.Class.dto.DayDTO;
import com.scon.project.admin.Class.dto.TimeDTO;

@Mapper
public interface ClassMapper {
	
	//강의리스트조회
	List<ClassDTO> selectClass();
	
	//강의상세보기조회
	List<ClassDTO> selectAllClass();
	
	
	//강의등록
	int registClass(ClassDTO classDTO);

	int insertClsAndDay(DayDTO dayDTO);
	
	int insertClsAndTime(TimeDTO timeDTO);
}
