package com.example.demo.repository.DAO;

import com.example.demo.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ObjectDAO<T> {

    List<T> getMany();

    Optional<T> getOne(UUID id) throws Exception;

    void create(T t) throws Exception ;

    void update(UUID id, T t) throws Exception;

    void delete(UUID id) throws Exception;
}
