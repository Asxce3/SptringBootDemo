package com.example.demo.model;

import org.springframework.data.annotation.Id;

import java.util.UUID;

public class Comment {
    @Id
    private UUID uuid;
    private UUID personUuid;
    private UUID restaurantUuid;
    private String comment;
    private int Score;

    public Comment(UUID personUuid, UUID restaurantUuid, String comment, int score) {
        this.personUuid = personUuid;
        this.restaurantUuid = restaurantUuid;
        this.comment = comment;
        this.Score = score;
    }

    public Comment() {}

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getPersonUuid() {
        return personUuid;
    }

    public void setPersonUuid(UUID personUuid) {
        this.personUuid = personUuid;
    }

    public UUID getRestaurantUuid() {
        return restaurantUuid;
    }

    public void setRestaurantUuid(UUID restaurantUuid) {
        this.restaurantUuid = restaurantUuid;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getScore() {
        return Score;
    }

    public void setScore(int score) {
        Score = score;
    }
}
