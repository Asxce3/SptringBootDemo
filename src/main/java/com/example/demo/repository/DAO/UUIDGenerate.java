package com.example.demo.repository.DAO;

import java.util.UUID;

public class UUIDGenerate {
    public static UUID generateID(){
        System.out.println(UUID.randomUUID());
        return UUID.randomUUID();
    }
}