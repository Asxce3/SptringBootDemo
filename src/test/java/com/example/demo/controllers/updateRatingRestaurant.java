package com.example.demo.controllers;

import com.example.demo.repository.RestaurantRepository;
import com.example.demo.service.RestaurantService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
public class updateRatingRestaurant {

    @Autowired
    RestaurantService service;

    @Test
    public void updateRatingRestaurant() {
        UUID restaurantId = UUID.fromString("0d4358ac-718c-419f-bf06-daab83de1cc3");
        service.updateRatingRestaurant(restaurantId, 4);
    }
}
