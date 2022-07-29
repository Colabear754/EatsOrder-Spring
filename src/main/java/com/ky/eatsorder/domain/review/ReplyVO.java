package com.ky.eatsorder.domain.review;

import java.sql.Date;

public class ReplyVO {
	private int reply_number;
	private int review_number;
	private Date regist_date;
	private String content;

	public ReplyVO() {
		super();
	}

	public ReplyVO(int reply_number, int review_number, Date regist_date, String content) {
		this.reply_number = reply_number;
		this.review_number = review_number;
		this.regist_date = regist_date;
		this.content = content;
	}

	public int getReply_number() {
		return reply_number;
	}

	public void setReply_number(int reply_number) {
		this.reply_number = reply_number;
	}

	public int getReview_number() {
		return review_number;
	}

	public void setReview_number(int review_number) {
		this.review_number = review_number;
	}

	public Date getRegist_date() {
		return regist_date;
	}

	public void setRegist_date(Date regist_date) {
		this.regist_date = regist_date;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
