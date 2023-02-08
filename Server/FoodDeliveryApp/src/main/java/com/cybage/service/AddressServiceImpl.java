package com.cybage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cybage.dao.AddressDao;
import com.cybage.model.Address;

@Service
@Transactional
public class AddressServiceImpl implements AddressService {
	@Autowired
	private AddressDao addressDao;

	@Override
	public Address save(Address address) {
		return addressDao.save(address);
	}

	@Override
	public Address findByArea(String searchByArea) {
		return addressDao.findByArea(searchByArea);
	}

	@Override
	public Address findByAddressId(int addressId) {
		return addressDao.findById(addressId).get();
	}
}
