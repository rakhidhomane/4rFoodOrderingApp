package com.cybage.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import com.cybage.dao.FeedbackDao;
import com.cybage.model.Feedback;
import com.cybage.model.User;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class FeedbackServiceImplTest {

    @MockBean
    private FeedbackDao repository;

    @Test
    void testAddComment() {
        Feedback feedback=new Feedback();
        feedback.setFeddback("delicious");

        Mockito.doReturn(feedback).when(repository).save(feedback);

        assertEquals("delicious", repository.save(feedback).getFeedback());
    }

    @Test
    void testDeleteFeedback() {

    }

    @Test
    void testFindAll() {
        List<Feedback> feedbackList=new ArrayList<>();
        Feedback feedback=new Feedback();
        Feedback feedback1=new Feedback();
        Feedback feedback2=new Feedback();

        feedbackList.add(feedback);
        feedbackList.add(feedback1);
        feedbackList.add(feedback2);

        Mockito.doReturn(feedbackList).when(repository).findAll();

        assertEquals(3, repository.findAll().size());
        
    }

    @Test
    void testFindFeedbackByUser() {
        List<Feedback> feedbackList=new ArrayList<>();
        User user=new User();
        Feedback feedback=new Feedback();
        feedback.setUser(user);
        Feedback feedback1=new Feedback();
        feedback1.setUser(user);

        feedbackList.add(feedback);
        feedbackList.add(feedback1);

        Mockito.doReturn(feedbackList).when(repository).findByUser(user);

        assertEquals(2, repository.findByUser(user).size());
    }
}
