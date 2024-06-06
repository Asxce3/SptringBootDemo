package com.example.demo.repository;

import com.example.demo.model.User;
import com.example.demo.repository.DAO.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;


@Component
public class UserRepository {
    @Autowired
    UserDAO userDAO;

    public ResponseEntity<?> getUsers(){
        try {
            List<User> users = userDAO.getUsers();
            return ResponseEntity.status(200).body(users);

        } catch(CannotGetJdbcConnectionException e) {

            e.printStackTrace();
            return ResponseEntity.status(500).body(e.getMessage());

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    public ResponseEntity<?> getUser(UUID id) {
        try {
            User user = userDAO.getUser(id);
            return ResponseEntity.status(200).body(user);

        } catch(CannotGetJdbcConnectionException e) {

            e.printStackTrace();
            return ResponseEntity.status(500).body(e.getMessage());

        }  catch (Exception e) {

            e.printStackTrace();
            return ResponseEntity.status(404).body(e.getMessage());
        }

    }

    public ResponseEntity<?> createUser(User user) {
        try {
            userDAO.createUser(user);
            return ResponseEntity.status(201).build();

        } catch(CannotGetJdbcConnectionException e) {

            e.printStackTrace();
            return ResponseEntity.status(500).body(e.getMessage());

        }  catch (Exception e) {

            e.printStackTrace();
            return ResponseEntity.status(400).body(e.getMessage());
        }

    }

    public ResponseEntity<?> updateUser(UUID id, User user) {
        try {
            userDAO.updateUser(id, user);
            return ResponseEntity.status(200).build();

        } catch(CannotGetJdbcConnectionException e) {

            e.printStackTrace();
            return ResponseEntity.status(500).body(e.getMessage());

        }  catch (Exception e) {

            e.printStackTrace();
            return ResponseEntity.status(404).body(e.getMessage());
        }

    }

    public ResponseEntity<?> deleteUser(UUID id) {
        try {
            userDAO.deleteUser(id);
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
