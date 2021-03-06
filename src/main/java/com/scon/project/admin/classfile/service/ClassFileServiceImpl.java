package com.scon.project.admin.classfile.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.scon.project.admin.Class.dto.ClassDTO;
import com.scon.project.admin.classfile.dao.ClassFileMapper;
import com.scon.project.admin.classfile.dto.ClassFileDTO;
import com.scon.project.admin.classfile.dto.TaskFileDTO;
import com.scon.project.common.paging.Criteria;
import com.scon.project.common.paging.Pagination;
import com.scon.project.member.model.dto.MemberDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("ClassFileService")
@Transactional
public class ClassFileServiceImpl implements ClassFileService {

	private final ClassFileMapper classFileMapper;

	@Autowired
	public ClassFileServiceImpl(ClassFileMapper classFileMapper) {
		this.classFileMapper = classFileMapper;
	}

	//강의첨부자료게시글 등록
	@Override
	public int registClassFile(ClassFileDTO classFileList) throws Exception {
		
		log.info("강사명 : {}", classFileList.getMemberName());
		log.info("강의명 : {}", classFileList.getClsName());

		int result = 0;
		
		for(TaskFileDTO files : classFileList.getFileList2() ) {
			result += classFileMapper.registFile(files);
		}
		
		int result2 = classFileMapper.registClassFile(classFileList); //파일등록
		
		if(result2 > 0 && result == classFileList.getFileList2().size()) {
			/* result2 = classFileMapper.registFile(files); */
			result = (result2 > 0 ) ? 1 : 0;
		} else {
			throw new Exception("강의첨부파일 등록 실패");
		}
		
		return result;
	}
	

	// 강사등록
	@Override
	public List<MemberDTO> selectMember() {
		return classFileMapper.selectMember();
	}

	// 강의명등록
	@Override
	public List<ClassDTO> selectClassName() {
		return classFileMapper.selectClassName();
	}

	
	
	// 강의첨부자료조회
	@Override
	public List<ClassFileDTO> selectClassFileList(Criteria cri) {
		return classFileMapper.selectClassFileList(cri);
	}
	

	//강의첨부게시글 전체 갯수
	@Override
	public int total(Criteria cri) {
		return classFileMapper.total(cri);
	}

	//파일조회
	@Override
	public List<TaskFileDTO> selectFiles(Criteria cri) {
	return classFileMapper.selectFiles(cri);
		}

	//강의첨부게시글 삭제
	@Override
	public boolean deleteClassFile(String fileId) throws Exception {

		int result = classFileMapper.deleteClassFile(fileId);
		
		if(result <= 0) {
			throw new Exception("강의첨부 삭제 실패");
		}
		
		return result > 0 ? true : false;
	}

}
