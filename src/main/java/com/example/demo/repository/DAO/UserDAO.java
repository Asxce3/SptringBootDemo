package com.example.demo.repository.DAO;

import com.example.demo.model.User;

import java.util.List;
import java.util.UUID;

public interface UserDAO {
    List<User> getUsers();

    User getUser(UUID id) throws Exception;

    void createUser(User user) throws Exception ;

    void updateUser(UUID id, User user) throws Exception;

    void deleteUser(UUID id) throws Exception;

}
