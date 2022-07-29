package com.ky.eatsorder.domain.member;

public class MemberLoginVO {
	private String email;
	private String password;

	public MemberLoginVO() {
		super();
	}

	public MemberLoginVO(String email, String password) {
		this.email = email;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
