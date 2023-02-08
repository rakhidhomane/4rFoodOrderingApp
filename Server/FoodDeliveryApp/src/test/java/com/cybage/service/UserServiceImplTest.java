package com.cybage.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.cybage.dao.UserDao;
import com.cybage.model.User;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class UserServiceImplTest {

    @MockBean
    UserDao repository;

    @Test
    void testDeleteUser() {

    }

    @Test
    void testFindAllUsers() {
        List<User> userList=new ArrayList<>();
        User user=new User();
        User user1=new User();

        userList.add(user);
        userList.add(user1);

        Mockito.doReturn(userList).when(repository).findAll();

        assertEquals(2, repository.findAll().size());
    }

    @Test
    void testFindByUserEmail() {
        User user=new User();
        user.setUserEmail("mark@gmail.com");
        user.setUserId(90);

        Mockito.doReturn(user).when(repository).findByUserEmail("mark@gmail.com");

        assertEquals(90, repository.findByUserEmail("mark@gmail.com").getUserId());

    }

    @Test
    void testFindByUserEmailAndUserPassword() {
        User user=new User();
        user.setUserEmail("mark@gmail.com");
        user.setUserPassword("111");
        user.setAttemptsCount(2);

        Mockito.doReturn(user).when(repository).findByUserEmailAndUserPassword("mark@gmail.com", "111");

        assertEquals(2, repository.findByUserEmailAndUserPassword("mark@gmail.com", "111").getAttemptsCount());
    }

    @Test
    void testFindByUserId() {
        User user=new User();
        user.setUserId(99);
        user.setUserName("elon chacha");
        Optional<User> user1=Optional.of(user);

        Mockito.doReturn(user1).when(repository).findById(99);

        assertEquals("elon chacha", repository.findById(99).get().getUserName());
    }

    @Test
    void testSaveUser() {
        User user=new User();
        user.setUserPassword("333");

        Mockito.doReturn(user).when(repository).save(user);

        assertEquals("333", repository.save(user).getUserPassword());
    }

    @Test
    void testSaveUser2() {
        User user=new User();
        user.setUserMobileNo("9876543210");

        Mockito.doReturn(user).when(repository).save(user);

        assertEquals("9876543210", repository.save(user).getUserMobileNo());
    }

    @Test
    void testUnblock() {
        User user=new User();
        user.setAttemptsCount(5);

        user.setAttemptsCount(0);

        Mockito.doReturn(user).when(repository).save(user);

        assertEquals(0, repository.save(user).getAttemptsCount());
    }

    @Test
    void testUpdateUser() {
        User user=new User();
        user.setUserPassword("999");

        Mockito.doReturn(user).when(repository).save(user);

        assertEquals("999", repository.save(user).getUserPassword());
    }

    @Test
    void testUserList() {
        List<User> userList=new ArrayList<>();
        User user=new User();
        User user1=new User();

        userList.add(user);
        userList.add(user1);

        Mockito.doReturn(userList).when(repository).findAll();

        assertEquals(2, repository.findAll().size());
    }

    @Test
    void testUsercount() {
        Mockito.doReturn(2L).when(repository).count();

        assertEquals(2L, repository.count());
    }
}
