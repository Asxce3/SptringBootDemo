package com.example.demo.Utils;


import com.example.demo.model.Restaurant;
import com.example.demo.model.RestaurantRating;
import com.example.demo.repository.RestaurantRepository;
import com.example.demo.service.RestaurantService;
import com.example.demo.service.restaurantUtils.RestaurantUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
public class RestaurantUtilsTest {

    @Autowired
    RestaurantService restaurantService;

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    RestaurantUtils restaurantUtils;




//    @Test
//    public void setRating() {
//        UUID id = UUID.fromString("8b353f6a-9096-442b-a7b6-2a1d81e8c612");
//        Restaurant restaurant = (Restaurant) restaurantService.getRestaurant(id).getBody();
//        UUID rateId = restaurant.getRestaurantRatingId();
//
//        RestaurantRating restaurantRating = (RestaurantRating) restaurantRepository.getRatingById(rateId).getBody();
//
//        int score = 4;
//        restaurantUtils.setRating(restaurantRating, score);
//
//    }
}
