package com.lcc.flower.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 种类
 * @author lcc
 *
 */
public class Catalog implements Serializable {
	private Integer catalogid;
	private String catalogname;
	private Set flowers = new HashSet(0);

	public Catalog() {
	}

	public Catalog(String catalogname, Set flowers) {
		this.catalogname = catalogname;
		this.flowers = flowers;
	}
	
	public Integer getCatalogid() {
		return this.catalogid;
	}

	public void setCatalogid(Integer catalogid) {
		this.catalogid = catalogid;
	}

	public String getCatalogname() {
		return this.catalogname;
	}

	public void setCatalogname(String catalogname) {
		this.catalogname = catalogname;
	}

	public Set getFlowers() {
		return this.flowers;
	}

	public void setFlowers(Set flowers) {
		this.flowers = flowers;
	}

}
