package com.lcc.flower.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import com.lcc.flower.model.Flower;
import com.lcc.flower.model.Orderitem;

/**
 * 购物车的实体类
 * @author lcc
 *
 */
public class Cart {
	private Map items;
	
	public Map getItems(){
		return items;
	}
	
	public void setItems(Map items){
		this.items=items;
	}
	
	public Cart(){
		if (items ==null) {
			items = new HashMap<Integer,Orderitem>();
		}
	}
	
	public void addFlower(int flowerId,Orderitem orderitem){
		if (items.containsKey(flowerId)) {
			Orderitem orderitem2=(Orderitem) items.get(flowerId);
			orderitem.setQuantity(orderitem2.getQuantity()+orderitem.getQuantity());
			items.put(flowerId, orderitem);
		}else {
			items.put(flowerId, orderitem);
		}
	}
	
	public void updateCart(int flowerId,int quantity){
		Orderitem orderitem = (Orderitem) items.get(flowerId);
		orderitem.setQuantity(quantity);
		items.put(flowerId, orderitem);
	}
	
	public int getTotalPrice(){
		int totalPrice = 0 ; 
		for(Iterator iterator  = items.values().iterator();iterator.hasNext();){
			Orderitem orderitem = (Orderitem) iterator.next();
			Flower flower = orderitem.getFlower();
			int quantity= orderitem.getQuantity();
			totalPrice += flower.getPrice()*quantity;
		}
		return totalPrice;
	}
}
