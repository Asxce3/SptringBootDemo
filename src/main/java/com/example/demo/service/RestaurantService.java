package com.example.demo.service;

import com.example.demo.model.Restaurant;
import com.example.demo.model.RestaurantRating;
import com.example.demo.repository.RestaurantRepository;
import com.example.demo.service.restaurantUtils.RestaurantUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.UUID;


@Component
public class RestaurantService {

    @Autowired
    private RestaurantRepository repository;

    @Autowired
    private RestaurantUtils restaurantUtils;

    public ResponseEntity<?> getRestaurants() {
        return repository.getRestaurants();
    }

    public ResponseEntity<?> getRestaurant(UUID id) {
        return repository.getRestaurant(id);
    }

    public ResponseEntity<?> createRestaurant(Restaurant restaurant) {
        return repository.createRestaurant(restaurant);
    }

    public ResponseEntity<?> updateRestaurant(UUID id, Restaurant restaurant) {
        return repository.updateRestaurant(id, restaurant);
    }

    public ResponseEntity<?> deleteRestaurant(UUID id) {
        RestaurantRating restaurantRating = (RestaurantRating) repository.getRatingByRestaurantId(id).getBody();
        repository.deleteRestaurantRating(restaurantRating.getId());
        return repository.deleteRestaurant(id);
    }

    public void updateRatingRestaurant(UUID restaurantId, int score) {
        try {

            RestaurantRating rating = (RestaurantRating) repository.getRatingByRestaurantId(restaurantId).getBody();
            restaurantUtils.setRating(rating, score);

            repository.updateRatingRestaurant(rating.getId(), rating);

        }   catch (Exception e) {
            e.printStackTrace();

        }

    }

    public void recalculateRating(UUID restaurantId, int score, int sumScore) {
        try {
            RestaurantRating rating = (RestaurantRating) repository.getRatingByRestaurantId(restaurantId).getBody();

            restaurantUtils.recalculateRating(rating, score, sumScore);

            repository.updateRatingRestaurant(rating.getId(), rating);

        }   catch (Exception e) {
            e.printStackTrace();

        }

    }



}
