package com.ky.eatsorder.domain.etc_classes;

import com.ky.eatsorder.domain.restaurant.RestaurantVO;
import com.ky.eatsorder.domain.review.ReviewVO;

public class ReviewDetailInfo {
	private ReviewVO review;
	private String writer;
	private RestaurantVO restaurant;
	private long overDate;
	private String orderedItems;

	public ReviewDetailInfo() {
		super();
	}

	public ReviewDetailInfo(ReviewVO review, String writer, RestaurantVO restaurant, long overDate,
			String orderedItems) {
		this.review = review;
		this.writer = writer;
		this.restaurant = restaurant;
		this.overDate = overDate;
		this.orderedItems = orderedItems;
	}

	public ReviewVO getReview() {
		return review;
	}

	public void setReview(ReviewVO review) {
		this.review = review;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public RestaurantVO getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(RestaurantVO restaurant) {
		this.restaurant = restaurant;
	}

	public long getOverDate() {
		return overDate;
	}

	public void setOverDate(long overDate) {
		this.overDate = overDate;
	}

	public String getOrderedItems() {
		return orderedItems;
	}

	public void setOrderedItems(String orderedItems) {
		this.orderedItems = orderedItems;
	}
}
