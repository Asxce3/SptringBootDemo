package com.example.demo.service.restaurantUtils;

import com.example.demo.model.RestaurantRating;
import org.springframework.stereotype.Component;


@Component
public class RestaurantUtils {

    public void setRating(RestaurantRating rating, int score) {

        int count = rating.getCountRatings() + 1;

        double rate = (rating.getRating() + score) / 2;

        rating.setRating(rate);
        rating.setCountRatings(count);

    }
}
