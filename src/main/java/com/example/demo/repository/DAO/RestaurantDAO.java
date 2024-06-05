package com.example.demo.repository.DAO;

import com.example.demo.model.Restaurant;

import java.util.List;

public interface RestaurantDAO {

    List<Restaurant> getRestaurants();

    Restaurant getRestaurant(String name) throws Exception;

    void createRestaurant(Restaurant restaurant);

    void updateRestaurant(String name, Restaurant restaurant) throws Exception;

    void deleteRestaurant(String name) throws Exception;
}
