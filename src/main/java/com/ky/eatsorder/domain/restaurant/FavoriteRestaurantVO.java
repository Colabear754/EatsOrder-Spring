package com.ky.eatsorder.domain.restaurant;

public class FavoriteRestaurantVO {
	private String email;
	private int rst_id;

	public FavoriteRestaurantVO() {
		super();
	}

	public FavoriteRestaurantVO(String email, int rst_id) {
		this.email = email;
		this.rst_id = rst_id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getRst_id() {
		return rst_id;
	}

	public void setRst_id(int rst_id) {
		this.rst_id = rst_id;
	}
}
