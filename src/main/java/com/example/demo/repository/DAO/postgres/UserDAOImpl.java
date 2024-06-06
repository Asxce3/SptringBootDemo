package com.example.demo.repository.DAO.postgres;

import com.example.demo.model.User;
import com.example.demo.repository.DAO.UUIDGenerate;
import com.example.demo.repository.DAO.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.UUID;

@Component
public class UserDAOImpl implements UserDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate = new JdbcTemplate();

    public List<User> getUsers() {
        List<User> users = jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(User.class));
        return users;
    }

    public User getUser(UUID id) throws Exception {
        User user = jdbcTemplate.query("SELECT * FROM Person WHERE id = ?",new Object[]{id},
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

    public void updateUser(UUID id, User user) throws Exception{

        jdbcTemplate.update("UPDATE Person SET email = ?, telephone = ?, country = ?" +
                        " WHERE id = ?",
                user.getEmail(),
                user.getTelephone(),
                user.getCountry(),
                id);

    }

    public void deleteUser(UUID id) throws Exception{
        int i = jdbcTemplate.update("DELETE FROM Person WHERE id = ?", id);
        if (i != 1) {
            throw new Exception("User not found");
        }


    }
}



