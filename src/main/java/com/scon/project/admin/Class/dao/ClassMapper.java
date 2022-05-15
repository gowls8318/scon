package com.scon.project.admin.Class.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.scon.project.admin.Class.dto.ClassDTO;
import com.scon.project.admin.Class.dto.DayDTO;
import com.scon.project.admin.Class.dto.TimeDTO;
import com.scon.project.common.paging.Criteria;
import com.scon.project.member.model.dto.MemberDTO;

@Mapper
public interface ClassMapper {
	

	//강의리스트조회
	List<ClassDTO> selectClassList(Criteria cri);
	
	//멤버조회(강사)
	/* List<MemberDTO> findAllmemberList(int clsId); */

	//강의상세보기조회
	ClassDTO classDetail(int clsId);
	
	//요일리스트조회
	List<DayDTO> selectDayList();
	
	//강의등록
	int registClass(ClassDTO classDTO);
	
	//강사등록
	List<MemberDTO> registMember();

	//날짜등록
	int insertClsAndDay(DayDTO dayDTO);
	
	//교시등록
	int insertClsAndTime(TimeDTO timeDTO);

	//강의삭제
	int deleteClass(int clsId);

	//강의상세보기수정
	int classUpdate(ClassDTO clsId);

	//요일수정
	int updateDay(DayDTO dayList);

	//교시수정
	int updateTime(TimeDTO time);

	//강의게시글 총 갯수
	int total(Criteria cri);








}