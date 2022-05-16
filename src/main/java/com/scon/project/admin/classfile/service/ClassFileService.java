package com.scon.project.admin.classfile.service;

import java.util.List;

import com.scon.project.admin.Class.dto.ClassDTO;
import com.scon.project.admin.classfile.dto.ClassFileDTO;
import com.scon.project.common.paging.Criteria;
import com.scon.project.member.model.dto.MemberDTO;

public interface ClassFileService {
	
	//강의첨부자료 리스트 조회
	List<ClassFileDTO> selectClassFileList(Criteria cri);
	
	//게시글 전체 갯수
	int total(Criteria cri);
	
	//강의첨부자료 등록
	int registClassFile(ClassFileDTO classFileList) throws Exception;

	//강사조회
	List<MemberDTO> selectMember();

	//강의명조회
	List<ClassDTO> selectClassName();




	
}
