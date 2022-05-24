package com.scon.project.admin.classfile.dto;

import java.util.List;

import com.scon.project.admin.Class.dto.ClassDTO;
import com.scon.project.member.model.dto.MemberDTO;

import lombok.Data;

@Data
public class ClassFileDTO {
	
	/*
	 	FILE_ID	NUMBER	No		1	첨부파일아이디
		CLS_ID	NUMBER	No		2	강의아이디
		CLF_NAME	VARCHAR2(500 BYTE)	No		3	강의참고자료명
		CLF_STATUS	VARCHAR2(2 BYTE)	Yes	"'Y'	"	4	강의첨부자료 게시 유무
	 */
	
	private int fileId;  
	private Integer clsId;
	private String clfName;
	private String clfStatus;
	private String memberName; //강사명
	private String memberId;
	private MemberDTO member;
	private ClassDTO classDTO;
	private String clsName;
	private List<ClassDTO> classNameList;
	private List<TaskFileDTO> fileList2;
	private String fileOrginName;
	private String fileSaveName;
	private String filePath;
	private String fileType;
	private TaskFileDTO fileList;
	/* private List<TaskFileDTO> fileList; */

	// 하나의 게시글에 여러개의 파일
	


}
