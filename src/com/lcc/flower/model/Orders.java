package com.lcc.flower.model;

import java.io.Serializable;
import java.security.Timestamp;
import java.util.HashSet;
import java.util.Set;

public class Orders implements Serializable {
	
	// Fields
	private Integer orderid;
	private User user;
	private Timestamp orderdate;
	private Set orderitems = new HashSet(0);

	// Constructors
	/** default constructor */
	public Orders() {
	}

	/** full constructor */
	public Orders(User user, Timestamp orderdate, Set orderitems) {
		this.user = user;
		this.orderdate = orderdate;
		this.orderitems = orderitems;
	}

	// Property accessors
	public Integer getOrderid() {
		return this.orderid;
	}

	public void setOrderid(Integer orderid) {
		this.orderid = orderid;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Timestamp getOrderdate() {
		return this.orderdate;
	}

	public void setOrderdate(Timestamp orderdate) {
		this.orderdate = orderdate;
	}

	public Set getOrderitems() {
		return this.orderitems;
	}

	public void setOrderitems(Set orderitems) {
		this.orderitems = orderitems;
	}

}
