package com.example.demo.repository.DAO.postgres;

import com.example.demo.model.User;
import com.example.demo.repository.DAO.UUIDGenerate;
import com.example.demo.repository.DAO.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;


import java.util.List;

@Component
public class UserDAOImpl implements UserDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate = new JdbcTemplate();

    public List<User> getUsers() {
        List<User> users = jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(User.class));
        return users;
    }

    public User getUser(String username) throws Exception {
        User user = jdbcTemplate.query("SELECT * FROM Person WHERE username = ?",new Object[]{username},
                        new BeanPropertyRowMapper<>(User.class))
                .stream().findAny().orElse(null);

        if (user == null) {
            throw new Exception("User not found");
        }
        return user;
    }

    public void createUser(User user) throws Exception{
        try {
            jdbcTemplate.update("INSERT INTO Person VALUES(?, ?, ?, ?, ?, ?)",
                    UUIDGenerate.generateID(),
                    user.getUsername(),
                    user.getPassword(),
                    user.getEmail(),
                    user.getTelephone(),
                    user.getCountry());
        }   catch (Exception e) {
            throw new Exception("User already exist");
        }

    }

    public void updateUser(String username, User user) throws Exception{

        jdbcTemplate.update("UPDATE Person SET email = ?, telephone = ?, country = ?" +
                        " WHERE username = ?",
                user.getEmail(),
                user.getTelephone(),
                user.getCountry(),
                username);

    }

    public void deleteUser(String username) throws Exception{
        int i = jdbcTemplate.update("DELETE FROM Person WHERE username = ?", username);
        if (i != 1) {
            throw new Exception("User not found");
        }


    }
}



