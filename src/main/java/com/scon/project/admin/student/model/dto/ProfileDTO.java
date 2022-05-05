package com.scon.project.admin.student.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileDTO {
	
	/*
	FILE_ID	NUMBER	No		1	첨부파일아이디
	FILE_ORGNAME	VARCHAR2(255 BYTE)	No		2	파일 원본명
	FILE_SAVENAME	VARCHAR2(255 BYTE)	No		3	저장 파일명
	FILE_PATH	VARCHAR2(1000 BYTE)	No		4	파일저장경로
	FILE_TYPE	VARCHAR2(10 BYTE)	No		5	파일타입
	FILE_THUM_PATH	VARCHAR2(300 BYTE)	Yes		6	파일 썸네일 경로
	FILE_THUM.NAME	VARCHAR2(255 BYTE)	Yes		7	썸네일 파일명
	
	*/
	
	private int fileId;							// 첨부파일 아이디
	private String fileOrginName;				// 파일 원본명
	private String fileSaveName;				// 파일 저장명
	private String filePath;				// 파일저장경로
	private String fileType;				// 파일타입
	private String fileThumnailPathName;		// 파일 썸네일 경로
	private String fileThumnailName;			// 썸네일 파일명

}
