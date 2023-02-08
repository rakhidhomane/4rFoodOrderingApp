package com.cybage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cybage.dao.OrderInfoDao;
import com.cybage.dao.UserOrderDao;
import com.cybage.model.FoodMenus;
import com.cybage.model.OrderInfo;
import com.cybage.model.UserOrder;

@Service
public class OrderInfoServiceImpl implements OrderInfoService{
	@Autowired
	OrderInfoDao orderInfoDao;
	
	@Autowired
	UserOrderDao userOrderDao;


	@Override
	public List<OrderInfo> getAllOrders() {
		List<OrderInfo> orders=orderInfoDao.findAll();
		System.out.println(orders);
		return orders;
	}

	@Override
	public void saveFoodItems(FoodMenus foodItem, UserOrder order, int quantity) {
		OrderInfo orderInfo=new OrderInfo();
		orderInfo.setFoodMenus(foodItem);
		orderInfo.setUserOrder(order);
		orderInfo.setQuantity(quantity);
		orderInfoDao.save(orderInfo);	
	}

	@Override
	public List<UserOrder> orderList() {
		return userOrderDao.findAll();
	}

	@Override
	public int ordercount() {
		return (int) userOrderDao.count();
	}
	

	public List<OrderInfo> getOrderDetails(int orderId) {
		UserOrder order = userOrderDao.findById(orderId).orElse(null);
		return orderInfoDao.findByUserOrder(order);
	}

	@Override
	public List<OrderInfo> findByFoodRating( ) {
		return orderInfoDao.findAll();
	}

	@Override
	public OrderInfo findUserOrderBySerialId(int sNumber) {
		return orderInfoDao.findById(sNumber).orElse(null);
	}

	@Override
	public void save(OrderInfo userOrderInfo) {
		orderInfoDao.save(userOrderInfo)	;	
	}


}
