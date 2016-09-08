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
		Query query = session.createQuery("From Flower order by flowerid desc ");
		query.setFirstResult(0);
		query.setMaxResults(4);
		List flowers = query.list();
		ts.commit();
		session.close();
		return flowers;
	}

	@Override
	public List getFlowerByCatalogidPaging(int catalogid, int currentPage, int pageSize) {
		Session session = sessionFactory.openSession();
		Transaction ts = session.beginTransaction();
		Query query = session.createQuery("FROM Flower where catalogid=" + catalogid);
		int startRow = (currentPage - 1) * pageSize;
		
		query.setFirstResult(startRow);
		query.setMaxResults(pageSize);
		List flowers =query.list();
		session.close();
		return flowers;
	}

	@Override
	public int getTotalByCatalog(int catalogid) {
		Session session = sessionFactory.openSession();
		Transaction ts =session.beginTransaction();
		Query query = session.createQuery("from Flower where catalogid="+catalogid);
		List flowers =query.list();
		session.close();
		return flowers.size();
	}

	@Override
	public Flower getFlowerById(int id) {
		Session session = sessionFactory.openSession();
		Transaction ts =session.beginTransaction();
		Query query = session.createQuery("from Flower where flowerid="+id);
		List flowers =query.list();
		session.close();
		return (Flower) flowers.get(0);
	}

	@Override
	public boolean addOrUpdateFlower(Flower flower) {
		Session session =sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.saveOrUpdate(flower);;
		
		session.flush();
		session.clear();
		transaction.commit();
		session.close();
		return true;
	}

	@Override
	public List getAllFlower() {
		Session session = sessionFactory.openSession();
		Transaction ts =  session.beginTransaction();
		Query query = session.createQuery("from Flower order by catalogid desc");
		List flowers =query.list();
		session.flush();
		session.clear();
		ts.commit();
		session.close();
		return flowers;
	}

	@Override
	public boolean deleteFlowerById(int id) {
		Session session =sessionFactory.openSession();
		Transaction ts =session.beginTransaction();
		Query query = session.createQuery("from Flower where flowerid="+id);
		List flowers =query.list();
		session.delete((Flower)flowers.get(0));
		ts.commit();
		session.close();
		return true;
	}

}
