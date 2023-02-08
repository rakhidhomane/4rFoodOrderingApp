package com.cybage.service;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import com.cybage.dao.AddressDao;
import com.cybage.model.Address;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
@SpringBootTest
public class AddressServiceImplTest {
    
    @MockBean
    private AddressDao repository;

    @Test
    void testFindByAddressId() {
        Address address=new Address();
        Optional<Address> address1=Optional.of(address);
        address.setAddressId(100);

        Mockito.when(repository.findById(100)).thenReturn(address1);

        assertEquals(100,repository.findById(100).get().getAddressId());
    }

    @Test
    void testFindByArea() {
        Address address=new Address();
        address.setArea("pune");

        Mockito.doReturn(address).when(repository).findByArea("pune");

        assertEquals("pune",repository.findByArea("pune").getArea());
    }

    @Test
    void testSave() {
        Address address=new Address();
        address.setPincode("411017");

        Mockito.doReturn(address).when(repository).save(address);

        assertEquals("411017",repository.save(address).getPincode());
    }
}
