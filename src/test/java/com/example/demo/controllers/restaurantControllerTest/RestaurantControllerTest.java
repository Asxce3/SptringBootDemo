package com.example.demo.controllers.restaurantControllerTest;

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
public class RestaurantControllerTest {

    @Autowired
    MockMvc mockMvc;


    @Test
    public void createTest() throws Exception {
        Map<String, String> map = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();

        map.put("name", "Burger King");
        String json = objectMapper.writeValueAsString(map);

        mockMvc.perform(post("/restaurant/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().is(201));
    }

    @Test
    public void updateTest() throws Exception {
        Map<String, String> map = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();

        map.put("name", "Burger King");
        String json = objectMapper.writeValueAsString(map);
        String id = "66514037-41b2-4237-b17c-be1b845bb58d";

        mockMvc.perform(put("/restaurant/update").param("id", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteTest() throws Exception {
        String id = "1e2974a3-ce51-4470-a5d7-1c0cf9f05f9f";

        mockMvc.perform(delete("/restaurant/delete").param("id", id)).andExpect(status().isOk());
    }

}
