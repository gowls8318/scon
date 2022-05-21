package com.scon.project.admin.classfile.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskFileDTO {
	
	
	/*
	    FILE_ID	NUMBER	No		1	첨부파일아이디
		FILE_ORGNAME	VARCHAR2(255 BYTE)	No		2	파일 원본명
		FILE_SAVENAME	VARCHAR2(255 BYTE)	No		3	저장 파일명
		FILE_PATH	VARCHAR2(1000 BYTE)	No		4	파일저장경로
		FILE_TYPE	VARCHAR2(10 BYTE)	No		5	파일타입
	 */
	
	private int fileId;		//PK					
	private String fileOrginName;				
	private String fileSaveName;				
	private String filePath;				
	private String fileType;	
	
	private List<TaskFileDTO> fileList2; //여러파일
	 

}
