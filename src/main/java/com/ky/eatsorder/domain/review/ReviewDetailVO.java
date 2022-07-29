package com.ky.eatsorder.domain.review;

import com.ky.eatsorder.domain.restaurant.RestaurantVO;

public class ReviewDetailVO {
	private ReviewVO review;
	private String writer;
	private int likeCount;
	private RestaurantVO restaurant;
	private long overDate;
	private String orderedItems;

	public ReviewDetailVO() {
		super();
	}

	public ReviewDetailVO(ReviewVO review, String writer, int likeCount, RestaurantVO restaurant, long overDate,
			String orderedItems) {
		this.review = review;
		this.writer = writer;
		this.likeCount = likeCount;
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

	public int getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
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
