package com.ky.eatsorder.domain.review;

public class ReviewLikeVO {
	private int review_number;
	private String email;

	public ReviewLikeVO() {
		super();
	}

	public ReviewLikeVO(int review_number, String email) {
		this.review_number = review_number;
		this.email = email;
	}

	public int getNumber() {
		return review_number;
	}

	public void setNumber(int review_number) {
		this.review_number = review_number;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
