package com.boardmall.pro.dto;

import com.boardmall.pro.dto.Criteria;

public class PageMakeVO {
	//시작 페이지
	private int startPage;
	//끝 페이지
	private int endPage;
	// 이전과 다음 페이지의 존재 여부
	private boolean prev , next;
	// 전체 게시물의 수
	private int total;
	//현재 페이지 & 한 화면에 표시할 게시물의 양
	private Criteria cri;
	
	
	public PageMakeVO(Criteria cri , int total) {
		this.cri = cri;
		this.total = total;
		//마지막 페이지
		this.endPage = (int)(Math.ceil(cri.getPageNum() / 10.0)) * 10;
		//현재 첫번째 페이지
		this.startPage = endPage - 9;
		
		//제일 마지막 페이지
		int realend = (int) (Math.ceil(total * 1.0 / cri.getAmount()));
		
		// 제일 마지막 페이지가 마지막 페이지보다 작으면 마지막 페이지를 제일 마지막 페이지에 맞춘다.
		if(this.endPage > realend) {
			this.endPage = realend;
		}
		
		// 시작 페이지의 값이 1보다 큰 경우
		this.prev = this.startPage > 1;
		// 마지막 페이지의 값이 찐막 페이지보다 작을 경우
		this.next = realend > this.endPage;
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
		return "PageMakeVO [startPage=" + startPage + ", endPage=" + endPage + ", prev=" + prev + ", next=" + next
				+ ", total=" + total + ", cri=" + cri + "]";
	}
	
	
	
	
}
