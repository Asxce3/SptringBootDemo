package com.example.demo.service.commentUtils;

import java.util.UUID;

public class RecalculateRate {
    private UUID restaurantId;
    private int score;
    private int sumScore;

    public RecalculateRate(UUID restaurantId, int score, int sumScore) {
        this.restaurantId = restaurantId;
        this.score = score;
        this.sumScore = sumScore;
    }

    public int getSumScore() {
        return sumScore;
    }

    public int getScore() {
        return score;
    }

    public UUID getRestaurantId() {
        return restaurantId;
    }

    public String toString() {
        return "restaurant Id " + restaurantId.toString() + "," + "Score " + score + "," + "sumScore " + sumScore;
    }
}
