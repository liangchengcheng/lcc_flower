package com.lcc.flower.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.lcc.flower.dao.IUserDAO;
import com.lcc.flower.model.Guashi;
import com.lcc.flower.model.User;

public class UserDAO implements IUserDAO {

	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public boolean addOrUpdateUser(User user) {
		Session session = sessionFactory.openSession();
		Transaction ts = session.beginTransaction();
		session.saveOrUpdate(user);
		ts.commit();
		session.close();
		return true;
	}

	@Override
	public User checkUser(User user) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Query query = session.createQuery("from User where username='" + user.getUsername() + "' and password='"
				+ user.getPassword() + "'and role='" + user.getRole() + "'");

		User user2 = new User();
		List list = query.list();
		transaction.commit();

		session.clear();
		session.close();
		if (list.size() != 0) {
			user2 = (User) list.get(0);
			return user2;
		}
		return null;
	}

	@Override
	public boolean guashiUser(int id) {
		Session session = sessionFactory.openSession();
		Transaction ts = session.beginTransaction();
		Query query = session.createQuery("from User where userid="+id);
		User user1 =(User)(query.list().get(0));
		Guashi guashi = new Guashi();
		guashi.setUser(user1);
		user1.setGuashi(guashi);
		session.saveOrUpdate(guashi);
		
		ts.commit();
		session.close();
		return true;
	}

	@Override
	public boolean jieguaUser(int id) {
		Session session = sessionFactory.openSession();
		Transaction ts =session.beginTransaction();
		Query query = session.createQuery("from User where userid="+id);
		List list =query.list();
		User user1=(User)list.get(0);
		session.delete(user1.getGuashi());
		ts.commit();
		session.close();
		return true;
	}

	@Override
	public List getGuashi() {
		Session session = sessionFactory.openSession();
		Transaction ts =session.beginTransaction();
		Query query = session.createQuery("from Guashi");
		List list = query.list();
		ts.commit();
		return list;
	}

}
