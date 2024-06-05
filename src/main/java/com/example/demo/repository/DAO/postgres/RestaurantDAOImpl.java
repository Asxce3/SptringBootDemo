package com.example.demo.repository.DAO.postgres;

import com.example.demo.model.Restaurant;
import com.example.demo.repository.DAO.RestaurantDAO;
import com.example.demo.repository.DAO.UUIDGenerate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RestaurantDAOImpl implements RestaurantDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate = new JdbcTemplate();


    @Override
    public List<Restaurant> getRestaurants() {
        List<Restaurant> restaurants = jdbcTemplate.query
                ("SELECT * FROM restaurant", new BeanPropertyRowMapper<>(Restaurant.class));
        return restaurants;
    }

    @Override
    public Restaurant getRestaurant(String name) throws Exception {
        Restaurant restaurant = jdbcTemplate.query
                ("SELECT * FROM restaurant WHERE name = ?",
                        new Object[]{name},
                        new BeanPropertyRowMapper<>(Restaurant.class))
                .stream().findFirst().orElse(null);

        if (restaurant == null) {
            throw new Exception("User not found");
        }
        return restaurant;
    }

    @Override
    public void createRestaurant(Restaurant restaurant) {
        jdbcTemplate.update("INSERT INTO restaurant VALUES(?, ?)",
                UUIDGenerate.generateID(),
                restaurant.getName()
        );

    }

    @Override
    public void updateRestaurant(String name, Restaurant restaurant) throws Exception {
        int i = jdbcTemplate.update("UPDATE restaurant SET name = ?",
                restaurant.getName()
        );

        if (i != 1) {
            throw new Exception("Restaurant not found");
        }
    }

    @Override
    public void deleteRestaurant(String name) throws Exception {
        int i = jdbcTemplate.update("DELETE FROM restaurant WHERE name = ?", name);

        if (i != 1) {
            throw new Exception("Restaurant not found");
        }
    }
}
