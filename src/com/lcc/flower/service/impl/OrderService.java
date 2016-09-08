package com.lcc.flower.service.impl;

import com.lcc.flower.dao.IOrderDAO;
import com.lcc.flower.model.Orders;
import com.lcc.flower.service.IOrderService;

public class OrderService implements IOrderService{

	private IOrderDAO orderDAO;
	
	public IOrderDAO getOrderDAO() {
		return orderDAO;
	}
	
	public void setOrderDAO(IOrderDAO orderDAO) {
		this.orderDAO = orderDAO;
	}
	
	@Override
	public Orders saveOrder(Orders order) {
		return orderDAO.saveOrder(order);
	}

}
