package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

//@Document("Users")
public class User {
    @Id
    private UUID uuid;

    private String username;
    private String password;
    private String email;
    private String telephone;
    private String country;

    public User(String username, String password, String email, String telephone, String country){
        this.username = username;
        this.password = password;
        this.email = email;
        this.telephone = telephone;
        this.country = country;
    }

    public User(){
    }

    public void setUUID(UUID uuid) {
        this.uuid = uuid;
    }
    public UUID getUUID() {
       return uuid;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String toString() {
        return "\nuuid : " + uuid + "\nusername : " + username + "\npassword : " + password
                + "\nemail : " + email + "\ntelephone : " + telephone + "\ncountry : " + country;
    }


}
