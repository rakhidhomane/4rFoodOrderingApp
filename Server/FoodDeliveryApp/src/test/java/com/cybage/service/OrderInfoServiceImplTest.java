package com.cybage.service;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import com.cybage.dao.OrderInfoDao;
import com.cybage.model.FoodMenus;
import com.cybage.model.OrderInfo;
import com.cybage.model.UserOrder;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class OrderInfoServiceImplTest {
    
    @MockBean
    private OrderInfoDao repository;

    @Test
    void testGetAllOrders() {
    	// Mockito.when(repository.findAll()).thenReturn(new ArrayList<>());
    	Mockito.doReturn(new ArrayList<>()).when(repository).findAll();
    	List<OrderInfo> orderInfoList= new ArrayList<>();
        OrderInfo orderInfo= new OrderInfo(1, new FoodMenus(), 4, "food was good", 2, new UserOrder());
        OrderInfo orderInfo1= new OrderInfo(2, new FoodMenus(), 3, "food was delicious", 1, new UserOrder());
        
        orderInfoList.add(orderInfo);
        orderInfoList.add(orderInfo1);

        Mockito.doReturn(orderInfoList).when(repository).findAll();

    	assertEquals(2,repository.findAll().size());
    }

    @Test
    void testGetOrderDetails() {

        List<OrderInfo> orderInfoList=new ArrayList<>();
        UserOrder userOrder=new UserOrder();
        userOrder.setOrderId(1);
        
        OrderInfo orderInfo= new OrderInfo(1, new FoodMenus(), 4, "food was good", 2, userOrder);
        OrderInfo orderInfo1= new OrderInfo(2, new FoodMenus(), 3, "food was delicious", 1, userOrder);

        orderInfoList.add(orderInfo);
        orderInfoList.add(orderInfo1);

        Mockito.doReturn(orderInfoList).when(repository).findByUserOrder(userOrder);

        assertEquals(2,repository.findByUserOrder(userOrder).size());
    }

    @Test
    void testSaveFoodItems() {
        
    }

    @Test
    void testOrderList() {
        
    }

    @Test
    void testOrderCount() {
        
    }

    @Test
    void testFindByFoodRating() {
        List<OrderInfo> orderInfoList=new ArrayList<>();
        OrderInfo orderInfo=new OrderInfo();
        orderInfo.setFoodRating(3);
        OrderInfo orderInfo1=new OrderInfo();
        orderInfo1.setFoodRating(3);

        orderInfoList.add(orderInfo);
        orderInfoList.add(orderInfo1);

        Mockito.doReturn(orderInfoList).when(repository).findAll();

        assertEquals(3, repository.findAll().get(0).getFoodRating());
    }

}
