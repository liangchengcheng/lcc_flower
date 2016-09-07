package com.lcc.flower.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.lcc.flower.dao.IFlowerDAO;
import com.lcc.flower.model.Flower;

//query.setFirstResult(0);//从第一条记录开始
//query.setMaxResults(4);//取出四条记录
public class FlowerDAO implements IFlowerDAO {
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public List getNewFlower() {
		Session session = sessionFactory.openSession();
		Transaction ts = session.beginTransaction();
		Query query = session .createQuery("From Flower order by flowerid desc ");
		query.setFirstResult(0);
		query.setMaxResults(4);
		List flowers =query.list();
		ts.commit();
		session.close();
		return flowers;
	}

	@Override
	public List getFlowerByCatalogidPaging(int catalogid, int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getTotalByCatalog(int catalogid) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Flower getFlowerById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addOrUpdateFlower(Flower flower) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List getAllFlower() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteFlowerById(int id) {
		// TODO Auto-generated method stub
		return false;
	}

}
