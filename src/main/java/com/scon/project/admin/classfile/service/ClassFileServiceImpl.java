package com.scon.project.admin.classfile.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.scon.project.admin.Class.dto.ClassDTO;
import com.scon.project.admin.classfile.dao.ClassFileMapper;
import com.scon.project.admin.classfile.dto.ClassFileDTO;
import com.scon.project.member.model.dto.MemberDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("ClassFileService")
@Transactional
public class ClassFileServiceImpl implements ClassFileService{
	
	private final ClassFileMapper classFileMapper;
	
	@Autowired
	public ClassFileServiceImpl(ClassFileMapper classFileMapper) {
		this.classFileMapper = classFileMapper;
	}
	
	
	@Override
	public int registClassFile(ClassFileDTO classFileList) {
		
		int result = classFileMapper.registClassFile(classFileList);
		
		
		
		return result;
	}


	//강사등록
	@Override
	public List<MemberDTO> selectMember() {
		return classFileMapper.selectMember();
	}

	//강의명등록
	
	 @Override public List<ClassDTO> selectClassName() {
	  
	 return classFileMapper.selectClassName(); }
	 

}
