package com.example.demo.service;

import com.example.demo.model.UserEdit;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.userUtils.UserUtils;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public class UserService {
    @Autowired
    UserRepository repository;

    @Autowired
    UserUtils userUtils;

    public ResponseEntity<?> getUsers() {
        return repository.getUsers();
    }

    public ResponseEntity<?> getUser(String username) {

        return repository.getUser(username);

    }

    public ResponseEntity<?> createUser(User user) {
        if (userUtils.validateUser(user)) {

            userUtils.setTelephoneUser(user);
            return repository.createUser(user);
        } else {
            return ResponseEntity.badRequest().body("Данные введены не корректно");
        }
    }

    public ResponseEntity<?> updateUser(String username, User user) {
        System.out.println(user);
        try {
            HttpStatusCode code = getUser(username).getStatusCode();
            if (code.is4xxClientError()) {
                return ResponseEntity.badRequest().body("Такой пользователь не существует");
            }
            if (userUtils.validateSocietyFields(user)) {
                userUtils.setTelephoneUser(user);
                return repository.updateUser(username, user);

            }
            return ResponseEntity.badRequest().body("Данные введены не корректно");

        }   catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("хз что случилось");
        }

    }

    public ResponseEntity<?> deleteUser(String username) {
        return repository.deleteUser(username);

    }

}




