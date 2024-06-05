package com.example.demo.repository;

import com.example.demo.model.Restaurant;
import com.example.demo.repository.DAO.RestaurantDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RestaurantRepository {
    @Autowired
    private RestaurantDAO restaurantDAO;

    public ResponseEntity<?> getRestaurants(){
        try {
            List<Restaurant> restaurants = restaurantDAO.getRestaurants();
            return ResponseEntity.status(200).body(restaurants);

        } catch(CannotGetJdbcConnectionException e) {

            e.printStackTrace();
            return ResponseEntity.status(500).body(e.getMessage());

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    public ResponseEntity<?> getRestaurant(String name) {
        try {
            Restaurant restaurant = restaurantDAO.getRestaurant(name);
            return ResponseEntity.status(200).body(restaurant);

        } catch(CannotGetJdbcConnectionException e) {

            e.printStackTrace();
            return ResponseEntity.status(500).body(e.getMessage());

        }  catch (Exception e) {

            e.printStackTrace();
            return ResponseEntity.status(404).body(e.getMessage());
        }

    }

    public ResponseEntity<?> createRestaurant(Restaurant restaurant) {
        try {
            restaurantDAO.createRestaurant(restaurant);
            return ResponseEntity.status(201).build();

        } catch(CannotGetJdbcConnectionException e) {

            e.printStackTrace();
            return ResponseEntity.status(500).body(e.getMessage());

        }  catch (Exception e) {

            e.printStackTrace();
            return ResponseEntity.status(400).body(e.getMessage());
        }

    }

    public ResponseEntity<?> updateRestaurant(String name, Restaurant restaurant) {
        try {
            restaurantDAO.updateRestaurant(name, restaurant);
            return ResponseEntity.status(200).build();

        } catch(CannotGetJdbcConnectionException e) {

            e.printStackTrace();
            return ResponseEntity.status(500).body(e.getMessage());

        }  catch (Exception e) {

            e.printStackTrace();
            return ResponseEntity.status(404).body(e.getMessage());
        }

    }

    public ResponseEntity<?> deleteRestaurant(String name) {
        try {
            restaurantDAO.deleteRestaurant(name);
            return ResponseEntity.status(200).build();

        } catch(CannotGetJdbcConnectionException e) {

            e.printStackTrace();
            return ResponseEntity.status(500).body(e.getMessage());

        }  catch (Exception e) {

            e.printStackTrace();
            return ResponseEntity.status(404).body(e.getMessage());
        }

    }
}


