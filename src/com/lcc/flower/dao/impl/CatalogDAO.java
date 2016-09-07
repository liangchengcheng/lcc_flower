package com.lcc.flower.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.lcc.flower.dao.ICatalogDAO;

public class CatalogDAO implements ICatalogDAO{

	private SessionFactory sessionFactory;
	public SessionFactory getSessionfactory(){
		return sessionFactory;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public List getAllCatalogs() {
		Session session = sessionFactory.openSession();
		Transaction transaction  = session.beginTransaction();
		Query query = session.createQuery("FROM Catalog");
		List catalogs = query.list();
		transaction.commit();
		session.close();
		return catalogs;
	}

}
