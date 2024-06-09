package com.example.demo.repository.DAO.postgres;

import com.example.demo.model.Restaurant;
import com.example.demo.model.RestaurantRating;
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
        String sql = "SELECT restaurant.id, restaurant.name, " +
                        "restaurant_rating.rating, restaurant_rating.count_ratings " +
                "FROM restaurant " +
                "INNER JOIN restaurant_rating " +
                "ON restaurant.id = restaurant_rating.restaurant_id";

        List<Restaurant> restaurants = jdbcTemplate.query
                (sql, new BeanPropertyRowMapper<>(Restaurant.class));

        return restaurants;
    }

    @Override
    public RestaurantRating getRatingById(UUID restaurantId) throws Exception{

        RestaurantRating restaurantRating = jdbcTemplate.query
                ("SELECT * FROM restaurant_rating WHERE restaurant_id = ?",
                        new Object[]{restaurantId},
                        new BeanPropertyRowMapper<>(RestaurantRating.class))
                .stream().findFirst().orElse(null);

        if (restaurantRating == null) {
            throw new Exception("Rating not found");
        }
        return restaurantRating;
    }


    @Override
    public Restaurant getRestaurant(UUID id) throws Exception {
        String sql = "SELECT restaurant.id, restaurant.name, " +
                "restaurant_rating.rating, restaurant_rating.count_ratings " +
                "FROM restaurant " +
                "INNER JOIN restaurant_rating " +
                "ON restaurant.id = restaurant_rating.restaurant_id " +
                "WHERE restaurant.id = ?";

        try {
            Restaurant restaurant = jdbcTemplate.query
                    (sql,
                            new Object[]{id},
                            new BeanPropertyRowMapper<>(Restaurant.class))
            .stream().findFirst().orElse(null);

            return restaurant;

        }   catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }

    }

    @Override
    public void createRestaurant(Restaurant restaurant) {

        UUID restaurantId = UUIDGenerate.generateID();

        jdbcTemplate.update("INSERT INTO restaurant VALUES(?, ?)",
                restaurantId,
                restaurant.getName());

        jdbcTemplate.update("INSERT INTO restaurant_rating VALUES(?, ?)",
                UUIDGenerate.generateID(), restaurantId);

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
    public void updateRatingRestaurant(RestaurantRating restaurantRating) throws Exception {
        int i = jdbcTemplate.update
                ("UPDATE restaurant_rating SET rating = ?, count_ratings = ? WHERE id = ?",
                    restaurantRating.getRating(),
                    restaurantRating.getCountRatings(),
                    restaurantRating.getId()
        );

        if (i != 1) {
            throw new Exception("restaurantRating not found");
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
