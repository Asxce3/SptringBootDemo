package com.example.demo.repository.DAO;

import com.example.demo.model.User;

import java.util.List;

public interface UserDAO {
    List<User> getUsers();

    User getUser(String username) throws Exception;

    void createUser(User user) throws Exception ;

    void updateUser(String username, User user) throws Exception;

    void deleteUser(String username) throws Exception;

}
