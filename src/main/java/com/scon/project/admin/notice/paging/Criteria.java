package com.scon.project.admin.notice.paging;

// Criteria는 rNum의 제한값과 현재 페이지, 페이지에 출력되는 게시물 숫자를 제어
public class Criteria {
	
	private int page;
	private int perPageNum;
	private int rowStart;
	private int rowEnd;
	
	public Criteria() {
		this.page = 1;
		this.perPageNum = 10;
	}
	
	public void setPage(int page) {
		if (
			page <= 0
		) {
			this.page = 1;
			return;
		}
		this.page = page;
	}
	
	public void setPerPageNum(int perPageNum)
	 {
	  if (perPageNum <= 0 || perPageNum > 100)
	  {
	   this.perPageNum = 10;
	   return;
	  }
	  this.perPageNum = perPageNum;
	 }

	 public int getPage()
	 {
	  return page;
	 }

	 public int getPageStart()
	 {
	  return (this.page - 1) * perPageNum;
	 }

	 public int getPerPageNum()
	 {
	  return this.perPageNum;
	 }

	 @Override
	 public String toString() {
	  return "Criteria [page=" + page + ", perPageNum=" + perPageNum + ""
	    + ", rowStart=" +  getRowStart() + ", rowEnd=" + getRowEnd()
	    + "]";
	 }

	 public int getRowStart() {
	  rowStart = ((page - 1) * perPageNum) + 1;
	  return rowStart;
	 }

	 public int getRowEnd() {
	  rowEnd = rowStart + perPageNum - 1;
	  return rowEnd;
	 }
	
	
	
	
	
	
	
	
	
	
	
////	현재 페이지 번호
//	private int currentPageNo;
//
////	페이지당 출력할 데이터 개수
//	private int recordsPerPage;
//	
////	화면 하단에 출력할 페이지 갯수
//	private int pageSize;
//	
////	검색 키워드
//	private String searchKeyword;
//	
////	검색 유형
//	private String searchType;
//	
//	public Criteria() {
////	기본값 지정
//		this.currentPageNo = 1;
//		this.recordsPerPage = 10;
//		this.pageSize = 10;		
//	}
//	
//	public int getStartPage() {
//		return (currentPageNo - 1) * recordsPerPage;
//	}
	
}
