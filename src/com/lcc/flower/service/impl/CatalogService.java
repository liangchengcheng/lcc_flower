package com.lcc.flower.service.impl;

import java.util.List;

import com.lcc.flower.dao.ICatalogDAO;
import com.lcc.flower.service.ICatalogService;

public class CatalogService implements ICatalogService{

	private ICatalogDAO catalogDAO;
	
	public ICatalogDAO getCatalogDAO() {
		return catalogDAO;
	}
	
	public void setCatalogDAO(ICatalogDAO catalogDAO) {
		this.catalogDAO = catalogDAO;
	}
	
	@Override
	public List getAllCatalogs() {
		return catalogDAO.getAllCatalogs();
	}

}
