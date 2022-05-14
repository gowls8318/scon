package com.scon.project.common.paging;

public class Pagination {

	private int startPage;			// 현재 화면의 시작 페이지
	private int endPage;			// 현재 화면의 마지막 페이지
	private int maxPage;			// 전체 페이지에서 가장 마지막 페이지
	private int buttonAmount = 10;	// 현재 화면의 버튼 갯수
	private boolean prev, next;		// 이전 페이지, 다음 페이지 존재 유무
	private int total;				// 전체 게시물 수
	private Criteria cri;			// 현재 페이지 & 한 페이지에 보여줄 게시물 수
	
	public Pagination(Criteria cri, int total) {
		
		this.cri = cri;
		this.total = total;
		
		/* 화면에 보여지는 마지막 페이지 - 10, 20, 30, ... */
		this.endPage = (int) (Math.ceil(cri.getPageNo() / (double) buttonAmount) * buttonAmount);
		
		/* 화면에 보여지는 시작 페이지 - 1, 11, 21, ... */
		this.startPage = (this.endPage - buttonAmount) + 1;
		
		/* 전체 중 가장 마지막 페이지 */
		this.maxPage = (int) (Math.ceil((double) total / cri.getLimit()));
		
		/* max 페이지가 더 작은 경우 마지막 페이지가 max페이지이다. */
		if(maxPage < endPage) {
			endPage = maxPage;
		}
		
		/* 마지막 페이지는 0이 될 수 없기 때문에 게시물이 아무 것도 존재하지 않으면 max페이지와 endPage는 0이 된다. */
		if(maxPage == 0 && endPage == 0) {
			maxPage = startPage;
			endPage = startPage;
		}
		
		/* 시작 페이지(startPage)값이 1보다 큰 경우 true */
		this.prev = this.startPage > 1;
		
		/* 마지막 페이지(endPage)값이 1보다 큰 경우 true */
		this.next = this.endPage < maxPage;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public Criteria getCri() {
		return cri;
	}

	public void setCri(Criteria cri) {
		this.cri = cri;
	}

	@Override
	public String toString() {
		return "Pagination [startPage=" + startPage + ", endPage=" + endPage + ", prev=" + prev + ", next=" + next
				+ ", total=" + total + ", cri=" + cri + "]";
	}

}
