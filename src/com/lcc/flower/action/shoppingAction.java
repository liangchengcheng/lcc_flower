package com.lcc.flower.action;

import java.util.Map;

import com.lcc.flower.model.Catalog;
import com.lcc.flower.model.Flower;
import com.lcc.flower.model.Orderitem;
import com.lcc.flower.service.IFlowerService;
import com.lcc.flower.util.Cart;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class shoppingAction extends ActionSupport {
	private int id;
	private int quantity;
	private IFlowerService flowerService;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public IFlowerService getFlowerService() {
		return flowerService;
	}

	public void setFlowerService(IFlowerService flowerService) {
		this.flowerService = flowerService;
	}

	/**
	 * 增加到购物车
	 * 
	 * @return
	 * @throws Exception
	 */
	public String addToCart() throws Exception {
		Flower flower = flowerService.getFlowerById(id);
		Orderitem orderitem = new Orderitem();
		orderitem.setFlower(flower);
		orderitem.setQuantity(quantity);
		Map session = (Map) ActionContext.getContext().getSession();
		Cart cart = (Cart) session.get("cart");
		if (cart == null) {
			cart = new Cart();
			cart.addFlower(id, orderitem);
		}
		cart.addFlower(id, orderitem);
		session.put("cart", cart);
		return SUCCESS;
	}
	
	/**
	 * 更新购物车
	 * @return
	 * @throws Exception
	 */
	public String updateCart() throws Exception {
		Map session = (Map) ActionContext.getContext().getSession();
		Cart cart = (Cart) session.get("cart");
		cart.updateCart(id, quantity);
		session.put("cart", cart);
		return SUCCESS;
	}

}
