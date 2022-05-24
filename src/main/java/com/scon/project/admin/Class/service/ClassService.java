package com.scon.project.admin.Class.service;

import java.util.List;
import com.scon.project.admin.Class.dto.ClassDTO;
import com.scon.project.admin.Class.dto.DayDTO;
import com.scon.project.admin.Class.dto.TimeDTO;
import com.scon.project.common.paging.Criteria;
import com.scon.project.member.model.dto.MemberDTO;

public interface ClassService {
	

	//강의등록
	boolean registClass(ClassDTO classDTO) throws Exception;

	//강의리스트조회
	List<ClassDTO> selectClassList(Criteria cri); 
	
	//강의상세조회
	ClassDTO classDetail(int clsId);

	//요일리스트조회
	List<DayDTO> selectDayList();
	
	//강의입력 - 강사명 가져오기
	//List<MemberDTO> findAllmemberList(int clsId);

	//강의삭제 - 강의PK값 가져오기
	int deleteClass(int clsId);

	//강의수정
	int classUpdate(ClassDTO classDTO) throws Exception;
	
	
	//요일수정 int updateDay(DayDTO dayList);
	 
	//교시수정 int updateTime(TimeDTO time);
	 
	

	//강사조회
	List<MemberDTO> registMember();

	//게시글 전체 갯수
	int total(Criteria cri);


	


	
}
