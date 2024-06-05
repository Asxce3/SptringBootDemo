package com.example.demo.controllers;

import com.example.demo.model.Restaurant;
import com.example.demo.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/get-many")
    public ResponseEntity<?> getUsers(){
        return restaurantService.getRestaurants();
    }

    @GetMapping("/get-one")
    public ResponseEntity<?> getUser(@RequestParam String name){
        return restaurantService.getRestaurant(name);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody() Restaurant restaurant){
        return restaurantService.createRestaurant(restaurant);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@RequestParam() String username, @RequestBody Restaurant restaurant) {
        return restaurantService.updateRestaurant(username, restaurant);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteUser(@RequestParam() String name ){
        return restaurantService.deleteRestaurant(name);
    }

}
