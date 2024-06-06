package com.example.demo.controllers;

import com.example.demo.model.UserEdit;
import com.example.demo.service.UserService;

import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/get-many")
    public ResponseEntity<?> getMany(){
        return userService.getUsers();
    }

    @GetMapping("/get-one")
    public ResponseEntity<?> getOne(@RequestParam UUID id){
        return userService.getUser(id);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody() User user ){
        return userService.createUser(user);
    }
    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestParam() UUID id, @RequestBody UserEdit user) {
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam() UUID id ){
        return userService.deleteUser(id);
    }

}



