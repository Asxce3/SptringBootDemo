package com.example.demo.repository;

import com.example.demo.model.User;
import com.example.demo.repository.DAO.ObjectDAO;
import com.example.demo.repository.DAO.postgres.UserDAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;


@Component
public class UserRepository {
    @Autowired
    ObjectDAO<User> userDAO;

    public ResponseEntity<?> getUsers(){
        try {
            List<User> users = userDAO.getMany();
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
            User user = userDAO.getOne(id);
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
            userDAO.create(user);
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
            userDAO.update(id, user);
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
            userDAO.delete(id);
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
