package com.lcc.flower.model;

import java.io.Serializable;

public class Guashi implements Serializable {
	
	// Fields
	private Integer id;
	private User user;

	// Constructors	
	public Guashi() {
	}

	public Guashi(User user) {
		this.user = user;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
