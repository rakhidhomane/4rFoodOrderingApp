package com.cybage.service;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.cybage.dao.ComplaintInfoDao;
import com.cybage.model.ComplaintInfo;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class ComplaintInfoServiceImplTest {

    @InjectMocks
    private OrderInfoServiceImpl orderInfoServiceImpl;
    
    @MockBean
    private ComplaintInfoDao repository;

    @Test
    void testFindComplaintById() {
        ComplaintInfo complaint=new ComplaintInfo();
        Optional<ComplaintInfo> complaint1=Optional.of(complaint);
        complaint.setComplaintId(50);

        Mockito.when(repository.findById(50)).thenReturn(complaint1);

        assertEquals(50,repository.findById(50).get().getComplaintId());
    }

    @Test
    void testGetAllComplaints() {
    	List<ComplaintInfo> complaintInfoList= new ArrayList<>();

        ComplaintInfo complaint=new ComplaintInfo();
        ComplaintInfo complaint1=new ComplaintInfo();

        complaintInfoList.add(complaint);
        complaintInfoList.add(complaint1);
        complaintInfoList.add(complaint);

        Mockito.doReturn(complaintInfoList).when(repository).findAll();

        assertEquals(3, repository.findAll().size());
    }

    @Test
    void testSave() {
        ComplaintInfo complaint=new ComplaintInfo();
        complaint.setComplaintMessage("complaint registered");

        Mockito.doReturn(complaint).when(repository).save(complaint);

        assertEquals("complaint registered",repository.save(complaint).getComplaintMessage());
    }
}
