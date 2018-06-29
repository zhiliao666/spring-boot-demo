package com.zhiliao.model;

import java.io.Serializable;

public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer userId;
	private String userName;
	private String email;
	private String phone;
	
	public User() {
	}
	
	public User(Integer userId, String userName, String email, String phone) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.email = email;
		this.phone = phone;
	}
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", email="
				+ email + ", phone=" + phone + "]";
	}
	
	
	
}
