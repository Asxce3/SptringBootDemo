package com.example.demo.model;

import org.springframework.data.annotation.Id;

import java.util.UUID;

public class Restaurant {
    @Id
    private UUID uuid;
    private String name;

    public Restaurant(String name) {
        this.name = name;
    }

    public Restaurant() {
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
