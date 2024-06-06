package com.example.demo.model;

import org.springframework.data.annotation.Id;

import java.util.UUID;

public class Restaurant {
    @Id
    private UUID id;
    private String name;

    public Restaurant(String name) {
        this.name = name;
    }

    public Restaurant() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
