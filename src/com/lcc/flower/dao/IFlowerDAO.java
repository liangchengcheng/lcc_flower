package com.lcc.flower.dao;

import java.util.List;

import com.lcc.flower.model.Flower;

public interface IFlowerDAO {
	public List getNewFlower();
	public List getFlowerByCatalogidPaging(int catalogid,int currentPage,int pageSize);
	public int getTotalByCatalog(int catalogid);
	public Flower getFlowerById(int id);
	public boolean addOrUpdateFlower(Flower flower);
	public List getAllFlower();
	public boolean deleteFlowerById(int id);
}
