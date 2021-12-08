package com.cognixia.jumpplus.model;

import java.io.Serializable;

public class Customer implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String email, userId, password;
	
	private Account account;

	public Customer(String email, String userId, String password, Account account) {
		super();
		this.email = email;
		this.userId = userId;;
		this.password = password;
		this.account = account;
	}

	public String getEmail() {
		return email;
	}

	public String getUserId() {
		return userId;
	}

	public String getPassword() {
		return password;
	}

	public Account getAccount() {
		return account;
	}
	

	public void setEmail(String email) {
		this.email = email;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setAccount(double depositAmount) {
		this.account = new Account(depositAmount);
	}
	
	
}
