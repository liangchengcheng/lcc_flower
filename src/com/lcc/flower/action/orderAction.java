package com.lcc.flower.action;

import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import java.sql.Timestamp;

import com.lcc.flower.model.Orderitem;
import com.lcc.flower.model.Orders;
import com.lcc.flower.model.User;
import com.lcc.flower.service.IOrderService;
import com.lcc.flower.util.Cart;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class orderAction extends ActionSupport {

	private IOrderService orderService;

	public IOrderService getOrderService() {
		return orderService;
	}

	public void setOrderService(IOrderService orderService) {
		this.orderService = orderService;
	}

	public String checkOut() throws Exception {
		Map session = (Map) ActionContext.getContext().getSession();
		Cart cart = (Cart) session.get("cart");
		User user = (User) session.get("user");
		if (user == null) {
			return ERROR;
		}

		Orders orders = new Orders();
		orders.setOrderdate(new Timestamp(new Date().getTime()));
		orders.setUser(user);

		for (Iterator iterator = cart.getItems().values().iterator(); iterator.hasNext();) {
			Orderitem orderitem = (Orderitem) iterator.next();
			orders.getOrderitems().add(orderitem);
			orderitem.setOrders(orders);
		}

		orderService.saveOrder(orders);
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("order", orders);
		session.remove("cart");
		return SUCCESS;
	}
}
