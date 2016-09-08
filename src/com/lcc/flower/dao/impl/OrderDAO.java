package com.lcc.flower.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import com.lcc.flower.dao.IOrderDAO;
import com.lcc.flower.model.Orders;

public class OrderDAO implements IOrderDAO{

	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public Orders saveOrder(Orders order) {
		Session session = sessionFactory.openSession();
		Transaction ts = session.beginTransaction();
		session.save(order);
		ts.commit();
		session.close();
		return order;
	}

}
