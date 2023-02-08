package com.cybage.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.cybage.dao.UserOrderDao;
import com.cybage.model.Restaurant;
import com.cybage.model.User;
import com.cybage.model.UserOrder;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class UserOrderServiceImplTest {

    @MockBean
    private UserOrderDao repository;

    @Test
    void testCancelOrder() {
        UserOrder order=new UserOrder();
        order.setOrderStatus("cancelled");

        Mockito.doReturn(order).when(repository).save(order);

        assertEquals("cancelled", repository.save(order).getOrderStatus());
    }

    @Test
    void testFindUserOrderByOrderId() {
        UserOrder order=new UserOrder();
        order.setOrderId(6);
        Optional<UserOrder> order1=Optional.of(order);

        Mockito.doReturn(order1).when(repository).findById(6);

        assertEquals(6, repository.findById(6).get().getOrderId());
    }

    @Test
    void testGetAllOrderForUser() {
        List<UserOrder> userOrderList=new ArrayList<>();
        User user=new User();
        user.setUserId(20);
        UserOrder order=new UserOrder();
        order.setUser(user);
        UserOrder order1=new UserOrder();
        order1.setUser(user);

        userOrderList.add(order);
        userOrderList.add(order1);

        Mockito.doReturn(userOrderList).when(repository).findByUser(user);

        assertEquals(20, repository.findByUser(user).get(1).getUser().getUserId());
    }

    @Test
    void testGetAllOrders() {
        List<UserOrder> userOrderList=new ArrayList<>();
        Restaurant restaurant=new Restaurant();
        restaurant.setRestId(43);
        UserOrder order=new UserOrder();
        order.setRestaurant(restaurant);
        UserOrder order1=new UserOrder();
        order1.setRestaurant(restaurant);

        userOrderList.add(order);
        userOrderList.add(order1);

        Mockito.doReturn(userOrderList).when(repository).findByRestaurant(restaurant);

        assertEquals(43, repository.findByRestaurant(restaurant).get(0).getRestaurant().getRestId());
    }

    @Test
    void testSave() {
        UserOrder userOrder=new UserOrder();
        userOrder.setTotal(500);

        Mockito.doReturn(userOrder).when(repository).save(userOrder);

        assertEquals(500, repository.save(userOrder).getTotal());
    }

    @Test
    void testSave2() {
        UserOrder userOrder=new UserOrder();
        userOrder.setOrderStatus("placed");

        Mockito.doReturn(userOrder).when(repository).save(userOrder);

        assertEquals("placed", repository.save(userOrder).getOrderStatus());
    }
}
