package com.cybage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cybage.dao.CartDao;
import com.cybage.model.Cart;

@Service
public class CartServiceImpl implements CartService{
    @Autowired
	CartDao cartDao;
    
	@Override
	public List<Cart> getAllCartItems() {	
		return cartDao.findAll();
	}

}
