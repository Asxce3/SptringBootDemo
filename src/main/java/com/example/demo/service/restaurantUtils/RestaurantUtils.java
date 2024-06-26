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

    public void recalculateRating(RestaurantRating rating, int score, int sumScore) {

        int countRatings = rating.getCountRatings();
        int countDeleteComment = 1;

        int newRating = (sumScore - score) / countRatings;

        rating.setRating(newRating);
        rating.setCountRatings(countRatings - countDeleteComment);

    }
}
