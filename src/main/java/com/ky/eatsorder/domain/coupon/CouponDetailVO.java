package com.ky.eatsorder.domain.coupon;

public class CouponDetailVO {
	private ValidCouponVO coupon;
	private int available_count;
	
	public CouponDetailVO() {
		super();
	}

	public CouponDetailVO(ValidCouponVO coupon, int available_count) {
		this.coupon = coupon;
		this.available_count = available_count;
	}

	public ValidCouponVO getCoupon() {
		return coupon;
	}

	public void setCoupon(ValidCouponVO coupon) {
		this.coupon = coupon;
	}

	public int getAvailable_count() {
		return available_count;
	}

	public void setAvailable_count(int available_count) {
		this.available_count = available_count;
	}
}
