package com.example.demo.controllers;

import com.example.demo.model.Restaurant;
import com.example.demo.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/get-many")
    public ResponseEntity<?> getMany(){
        return restaurantService.getRestaurants();
    }

    @GetMapping("/get-one")
    public ResponseEntity<?> getOne(@RequestParam UUID id){
        return restaurantService.getRestaurant(id);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody() Restaurant restaurant){
        return restaurantService.createRestaurant(restaurant);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestParam() UUID id, @RequestBody Restaurant restaurant) {
        return restaurantService.updateRestaurant(id, restaurant);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam() UUID id){
        return restaurantService.deleteRestaurant(id);
    }

}
