package com.example.demo.repository.DAO;

import com.example.demo.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface UserMongoDAO extends MongoRepository<User, String> {

    @Query(value = "{username: '?0'}")
    User findUserByName(String username);

}
