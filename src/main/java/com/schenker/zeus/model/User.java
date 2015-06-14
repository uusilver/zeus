package com.schenker.zeus.model;

public class User {
	private String userId = null;
	private String userPassword = null;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	
	public User() {
		
	}
	
	public User(String userId2, String userPassword2) {
		this.userId = userId2;
		this.userPassword = userPassword2;
	}
	@Override
	public String toString() {		
		return "userId: " + userId + ",userPassword: " + userPassword;
	}
}
