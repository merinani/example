package com.vhs.web.model;

public class UserData {
	private String userName;
    private boolean isAdmin;
	private boolean isLogged;

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public boolean getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public boolean getIsLogged() {
		return isLogged;
	}
	public void setIsLogged(boolean isLogged) {
		this.isLogged = isLogged;
	}
}