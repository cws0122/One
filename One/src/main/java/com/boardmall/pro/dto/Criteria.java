package com.boardmall.pro.dto;

public class Criteria {
	
	// 현재 페이지
	private int pageNum;
	// 페이지당 표시할 게시물 수
	private int amount;
	
	//기본 생성자 현제페이지 1 , 게시물 수는 10으로 설정
	public Criteria() {
		this(1,10);
	}
	
	
	// 원하는 페이지와 게시물 수를 사용할 때 사용하는 생성자
	public Criteria(int pageNum , int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
	}


	public int getPageNum() {
		return pageNum;
	}


	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}


	public int getAmount() {
		return amount;
	}


	public void setAmount(int amount) {
		this.amount = amount;
	}


	@Override
	public String toString() {
		return "Criteria [pageNum=" + pageNum + ", amount=" + amount + "]";
	}
	
	
	
	
	
}
