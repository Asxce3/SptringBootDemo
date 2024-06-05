package com.example.demo.controllers;

import com.example.demo.model.UserEdit;
import com.example.demo.service.UserService;

import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/get-many")
    public ResponseEntity<?> getUsers(){
        return userService.getUsers();
    }

    @GetMapping("/get-one")
    public ResponseEntity<?> getUser(@RequestParam String username){
        return userService.getUser(username);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody() User user ){
        return userService.createUser(user);
    }
    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@RequestParam() String username, @RequestBody UserEdit user) {
        return userService.updateUser(username, user);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteUser(@RequestParam() String username ){
        return userService.deleteUser(username);
    }

}



