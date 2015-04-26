package com.example.zad2.domain;

import java.util.ArrayList;
import java.util.List;

public class UserData {

	private String username;
	
	private String password;
	
	private String email;
	
	private boolean premium;
	
	private List<AdresData> adresy = new ArrayList<AdresData>();

	public List<AdresData> getAdresy() {
		return adresy;
	}

	public void setAdresy(List<AdresData> adresy) {
		this.adresy = adresy;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isPremium() {
		return premium;
	}

	public void setPremium(boolean premium) {
		this.premium = premium;
	}
	
	
}
