package com.ky.eatsorder.domain.restaurant;

public class RestaurantDetailVO {
	private RestaurantVO restaurant;
	private int reviewCount;
	private int replyCount;
	private double rating;

	public RestaurantDetailVO() {
		super();
	}

	public RestaurantDetailVO(RestaurantVO restaurant, int reviewCount, int replyCount, double rating) {
		this.restaurant = restaurant;
		this.reviewCount = reviewCount;
		this.replyCount = replyCount;
		this.rating = rating;
	}

	public RestaurantVO getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(RestaurantVO restaurant) {
		this.restaurant = restaurant;
	}

	public int getReviewCount() {
		return reviewCount;
	}

	public void setReviewCount(int reviewCount) {
		this.reviewCount = reviewCount;
	}

	public int getReplyCount() {
		return replyCount;
	}

	public void setReplyCount(int replyCount) {
		this.replyCount = replyCount;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}
}
