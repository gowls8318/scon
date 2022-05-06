package com.scon.project.admin.Class.service;

import java.util.List;
import com.scon.project.admin.Class.dto.ClassDTO;
import com.scon.project.member.model.dto.MemberDTO;

public interface ClassService {
	

	boolean registClass(ClassDTO classDTO) throws Exception;

	//강의리스트조회
	List<ClassDTO> selectClassList(); 
	
	//강의상세조회
	//List<ClassDTO> selectAllClass();

	//강의입력 - 강사명 가져오기
	List<MemberDTO> findAllmemberList(int clsId);

	

}
