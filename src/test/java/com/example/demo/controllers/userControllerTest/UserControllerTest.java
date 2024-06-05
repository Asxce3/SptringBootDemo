package com.example.demo.controllers.userControllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    String[] userJsons = {
            "{\"username\":\"Alice123\",\"password\":\"Password1\",\"telephone\":\"+1 202-555-0178\",\"email\":\"alice@example.com\"}",
            "{\"username\":\"Bob_456\",\"password\":\"SecurePass2\",\"email\":\"bob_456@example.org\"}",
            "{\"username\":\"Charlie_789\",\"password\":\"CharliePass3\",\"telephone\":\"+39 06 1234 5678\",\"email\":\"charlie_789@example.net\"}",
            "{\"username\":\"Diana.C\",\"password\":\"DianaPass4\",\"telephone\":\"+7 495 123-45-67\"}",
            "{\"username\":\"Edward_007\",\"password\":\"EdwardPass5\"}"
    };


    @Test
    public void createUser1() throws Exception {
        String user = userJsons[0];
        mockMvc.perform(post("/user/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(user))
                .andExpect(status().is(201));
    }

    @Test
    public void createUser2() throws Exception {
        String user = userJsons[1];
        mockMvc.perform(post("/user/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(user))
                .andExpect(status().is(201));
    }

    @Test
    public void createUser3() throws Exception {
        String user = userJsons[2];
        mockMvc.perform(post("/user/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(user))
                .andExpect(status().is(201));
    }

    @Test
    public void createUser4() throws Exception {
        String user = userJsons[3];
        mockMvc.perform(post("/user/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(user))
                .andExpect(status().is(201));
    }

    @Test
    public void createUser5() throws Exception {
        String user = userJsons[4];
        mockMvc.perform(post("/user/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(user))
                .andExpect(status().is(400));
    }


    @Test
    public void createUser6() throws Exception {
        Map<String, String> map = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();
//        map.put("username", "Kalbasa");
//        map.put("password", "Password123");
//        map.put("telephone", "+77083723603");

        map.put("qwe", "qwe");
        map.put("cffasd", "asdfsadfas");
        map.put("trf", "qwe");

        String json = objectMapper.writeValueAsString(map);

        mockMvc.perform(post("/user/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().is(201));
    }


    @Test
    public void deleteUser() throws Exception {

        mockMvc.perform(delete("/user/delete").param("username", "Kalbasa"))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteUsers() throws Exception {

        String[] users = {
                "Alice123",
                "Bob_456",
                "Charlie_789",
                "Diana.C",
                "Edward_007"
        };

        for (String username : users) {
            mockMvc.perform(delete("/user/delete").param("username", username))
                    .andExpect(status().isOk());
        }
    }

    @Test
    public void updateUser() throws Exception {
        Map<String, String > map = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();

//        map.put("password", "Password1234");
//        map.put("email", "Samvel@example.org");
        map.put("telephone", "+374 555 35 35");

        String json = objectMapper.writeValueAsString(map);

        String username = "Samvel1234";
        String wrongUsername = "baka";
        mockMvc.perform(put("/user/update").param("username", username)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().is(200));
    }
}
