package com.zph.pojo;

import java.util.Date;

public class User {
	private String username;
	private String phone;
	private String address;
	private String other;
	private String time;

	public String getUsername() {
		return username;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", phone=" + phone + ", address="
				+ address + ", other=" + other + ", time=" + time + "]";
	}

}
