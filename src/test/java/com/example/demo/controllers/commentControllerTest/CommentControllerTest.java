package com.example.demo.controllers.commentControllerTest;


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
public class CommentControllerTest {

    @Autowired
    MockMvc mockMvc;


    @Test
        public void createCommentTest() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> map = new HashMap<>();
        String userId = "8c692c41-85d1-4d73-977b-b2d8513dde17";
        String restaurantId = "8805bb43-eea9-48a5-b287-07ad1d5fec4f";

        map.put("personUuid", userId);
        map.put("restaurantUuid", restaurantId);
        map.put("comment", "Нормальное заведение");
        map.put("score", "4");

        String comment = objectMapper.writeValueAsString(map);

        mockMvc.perform(post("/comment/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(comment))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void updateCommentTest() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> map = new HashMap<>();

        map.put("uuid", "43d9d0e8-d4ad-41a6-be54-820dba8f6789");
        map.put("comment", "Такое себе заведение");
        map.put("score", "2");

        String comment = objectMapper.writeValueAsString(map);

        mockMvc.perform(put("/comment/update")
                        .param("uuid", "43d9d0e8-d4ad-41a6-be54-820dba8f6789")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(comment))
                .andExpect(status().is(200));
    }

    @Test
    public void deleteCommentTest() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> map = new HashMap<>();

        String comment = objectMapper.writeValueAsString(map);
        String id = "20339129-ae57-47da-be0f-08b8326e8489";

        mockMvc.perform(delete("/comment/delete")
                        .param("uuid", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(comment))
                .andExpect(status().is(200));
    }



}
