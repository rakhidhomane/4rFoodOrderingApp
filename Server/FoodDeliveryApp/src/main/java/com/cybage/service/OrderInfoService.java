package com.cybage.service;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.cybage.model.FoodMenus;
import com.cybage.model.OrderInfo;
import com.cybage.model.UserOrder;

public interface OrderInfoService {
   public List<OrderInfo> getAllOrders();

   public void saveFoodItems(FoodMenus foodItem, UserOrder order, int quantity);
   
   @Query("select o.order_id ,o.order_status,o. order_time, o.total from UserOrder o ")
   List<UserOrder> orderList();
   
   int ordercount();
   
   public List<OrderInfo> getOrderDetails(int orderID);
   List<OrderInfo> findByFoodRating();

    public OrderInfo findUserOrderBySerialId(int sNumber);

	public void save(OrderInfo userOrderInfo);

//   List<OrderInfo> findByFoodRating(int rating);

//   public void saveFoodItems(FoodMenus foodItem, UserOrder order);
//


}
