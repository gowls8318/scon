package com.scon.project.admin.classfile.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.scon.project.admin.Class.dto.ClassDTO;
import com.scon.project.admin.classfile.dto.ClassFileDTO;
import com.scon.project.admin.classfile.dto.TaskFileDTO;
import com.scon.project.common.paging.Criteria;
import com.scon.project.common.paging.Pagination;
import com.scon.project.member.model.dto.MemberDTO;


@Mapper
public interface ClassFileMapper {

	//첨부자료조회
	List<ClassFileDTO> selectClassFileList(Criteria cri);

	//강의첨부자료 게시글 페이징
	int total(Criteria cri);
	
	//강의첨부자료 등록
	int registClassFile(ClassFileDTO classFileList);
	
	//첨부파일등록
	int registFile(TaskFileDTO files);
	//1. 먼저 중간 첨부파일 테이블을 먼저 등록 -> 2. 본인 강의첨부파일테이블 등록해야한다.

	//강사등록
	List<MemberDTO> selectMember();

	//강의명등록
	List<ClassDTO> selectClassName();

	//파일조회
	List<TaskFileDTO> selectFiles(Criteria cri);

	//강의첨부 삭제
	int deleteClassFile(String fileId);


	
	
}
