package com.example.demo.service;

import com.example.demo.model.Restaurant;
import com.example.demo.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;


@Component
public class RestaurantService {

    @Autowired
    private RestaurantRepository repository;

    public ResponseEntity<?> getRestaurants(){
        return repository.getRestaurants();
    }

    public ResponseEntity<?> getRestaurant(String name) {
        return repository.getRestaurant(name);
    }

    public ResponseEntity<?> createRestaurant(Restaurant restaurant) {
        return repository.createRestaurant(restaurant);

    }

    public ResponseEntity<?> updateRestaurant(String name, Restaurant restaurant) {
        return repository.updateRestaurant(name, restaurant);
    }

    public ResponseEntity<?> deleteRestaurant(String name) {
        return repository.deleteRestaurant(name);
    }

}
