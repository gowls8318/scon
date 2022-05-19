package com.scon.project.common.paging;

public class Criteria {

	private int pageNo;			// 현재 페이지
	private int limit;			// 한 페이지에 보여줄 게시물 수
	private String keyword;		// 검색 키워드
	private String type;		// 검색 타입
	private String memberId;	// 원생 페이지 본인 확인용
	private int clsId;			// 강의 번호
	
	/* 1 페이지 당 게시물을 10개 씩 보여준다. */
	public Criteria() {
		this.pageNo = 1;
		this.limit = 10;
	}
	
	public Criteria(int pageNo, int limit) {
		this.pageNo = pageNo;
		this.limit = limit;
	}

	public int getPageNo() {
		return pageNo;
	}

	public int getClsId() {
		return clsId;
	}

	public void setClsId(int clsId) {
		this.clsId = clsId;
	}

	public void setPageNo(int pageNo) {
		
		if(pageNo <= 0) {
			this.pageNo = 1;
			return;
		}
		
		this.pageNo = pageNo;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		
		if(limit <= 0 || limit > 100) {
			this.pageNo = 10;
			return;
		}
		
		this.limit = limit;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	@Override
	public String toString() {
		return "Criteria [pageNo=" + pageNo + ", limit=" + limit + ", keyword=" + keyword + ", type=" + type
				+ ", memberId=" + memberId + ", clsId=" + clsId + "]";
	}
	
}
