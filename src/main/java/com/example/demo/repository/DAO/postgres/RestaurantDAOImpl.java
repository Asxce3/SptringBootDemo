package com.example.demo.repository.DAO.postgres;

import com.example.demo.model.Restaurant;
import com.example.demo.repository.DAO.RestaurantDAO;
import com.example.demo.repository.DAO.UUIDGenerate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

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
    public Restaurant getRestaurant(UUID id) throws Exception {
        Restaurant restaurant = jdbcTemplate.query
                ("SELECT * FROM restaurant WHERE id = ?",
                        new Object[]{id},
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
    public void updateRestaurant(UUID id, Restaurant restaurant) throws Exception {
        int i = jdbcTemplate.update("UPDATE restaurant SET name = ? WHERE id = ?",
                restaurant.getName(),
                id
        );

        if (i != 1) {
            throw new Exception("Restaurant not found");
        }
    }

    @Override
    public void deleteRestaurant(UUID id) throws Exception {
        int i = jdbcTemplate.update("DELETE FROM restaurant WHERE id = ?", id);

        if (i != 1) {
            throw new Exception("Restaurant not found");
        }
    }
}
