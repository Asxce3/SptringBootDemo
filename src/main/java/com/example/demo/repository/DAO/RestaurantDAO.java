package com.example.demo.repository.DAO;

import com.example.demo.model.Restaurant;

import java.util.List;
import java.util.UUID;

public interface RestaurantDAO {

    List<Restaurant> getRestaurants();

    Restaurant getRestaurant(UUID id) throws Exception;

    void createRestaurant(Restaurant restaurant);

    void updateRestaurant(UUID id, Restaurant restaurant) throws Exception;

    void deleteRestaurant(UUID id) throws Exception;
}
