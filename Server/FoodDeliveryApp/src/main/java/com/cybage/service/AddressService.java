package com.cybage.service;

import com.cybage.model.Address;

public interface AddressService {
	public Address save(Address address);
	public Address findByArea(String searchByArea);
	public Address findByAddressId(int addressId);
}
