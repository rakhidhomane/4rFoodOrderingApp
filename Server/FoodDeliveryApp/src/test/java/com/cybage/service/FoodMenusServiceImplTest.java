package com.cybage.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.cybage.dao.FoodMenusDao;
import com.cybage.model.FoodMenus;
import com.cybage.model.Restaurant;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class FoodMenusServiceImplTest {

    @MockBean
    FoodMenusDao repository;

    @Test
    void testDeleteFoodMenu() {

    }

    @Test
    void testFindAllFoodMenus() {
        List<FoodMenus> foodMenusList=new ArrayList<>();
        FoodMenus foodMenus=new FoodMenus();
        foodMenusList.add(foodMenus);

        Mockito.doReturn(foodMenusList).when(repository).findAll();

        assertEquals(1, repository.findAll().size());
    }

    @Test
    void testFindByFoodId() {

    }

    @Test
    void testFindById() {
        List<FoodMenus> foodMenusList=new ArrayList<>();
        FoodMenus foodMenus=new FoodMenus();
        foodMenus.setFoodId(20);
        Optional<FoodMenus> foodMenu=Optional.of(foodMenus);
        foodMenusList.add(foodMenus);

        Mockito.doReturn(foodMenu).when(repository).findById(20);

        assertEquals(foodMenu, repository.findById(20));
    }

    @Test
    void testFindByOffer() {
        List<FoodMenus> foodMenusList=new ArrayList<>();
        FoodMenus foodMenus=new FoodMenus();
        foodMenus.setOffer(200.0);
        FoodMenus foodMenus1=new FoodMenus();
        foodMenus1.setOffer(200.0);

        foodMenusList.add(foodMenus);
        foodMenusList.add(foodMenus1);

        Mockito.doReturn(foodMenusList).when(repository).findByOffer(200.0);

        assertEquals(2, repository.findByOffer(200.0).size());
    }

    @Test
    void testFindByOfferNot() {

    }

    @Test
    void testGetByRestaurantId() {
        List<FoodMenus> foodMenusList=new ArrayList<>();
        Restaurant restaurant=new Restaurant();
        restaurant.setRestId(5);
        FoodMenus foodMenus=new FoodMenus();
        foodMenus.setRestaurant(restaurant);
        FoodMenus foodMenus1=new FoodMenus();
        foodMenus1.setRestaurant(restaurant);

        foodMenusList.add(foodMenus);
        foodMenusList.add(foodMenus1);

        Mockito.doReturn(foodMenusList).when(repository).findFoodByRestaurantId(5);

        assertEquals(2, repository.findFoodByRestaurantId(5).size());
    }

    @Test
    void testSave() {
        FoodMenus foodMenus=new FoodMenus();
        foodMenus.setFoodId(4);

        Mockito.doReturn(foodMenus).when(repository).save(foodMenus);

        assertEquals(4, repository.save(foodMenus).getFoodId());
    }

    @Test
    void testUpdateFoodMenu() {
        FoodMenus foodMenus=new FoodMenus();
        foodMenus.setFoodCategory("veg");

        Mockito.doReturn(foodMenus).when(repository).save(foodMenus);

        assertEquals("veg", repository.save(foodMenus).getFoodCategory());
    }

    @Test
    void testUpdateFoodMenu2() {

    }

    @Test
    void testUpdateOffer() {
        FoodMenus foodMenus=new FoodMenus();
        foodMenus.setOffer(35);

        Mockito.doReturn(foodMenus).when(repository).save(foodMenus);

        assertEquals(35, repository.save(foodMenus).getOffer());
    }
}
