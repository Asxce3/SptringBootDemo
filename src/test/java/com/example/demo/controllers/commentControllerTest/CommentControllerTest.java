package com.example.demo.controllers.commentControllerTest;


import com.example.demo.model.Restaurant;
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

        String userId = "e1322807-e5c6-45bb-bf9d-89f84b5b745c";
//        String userId = "c8ae8526-07a5-4b02-93b1-b2e280c48441";

//    ОШИБКА ПРИ ВВОДЕ НЕСУЩЕТСВУЮЩЕГО РЕСТОРАНА !!!!!!
        String restaurantId = "1e2974a3-ce51-4470-a5d7-1c0cf9f05f9f";

//        String comment = "Отлично";
        String comment = "Хорошо";
//        String comment = "Паршиво";

//        String score = "5";
        String score = "4";
//        String score = "3";


        map.put("personId", userId);
        map.put("restaurantId", restaurantId);
        map.put("comment", comment);
        map.put("score", score);

        String json = objectMapper.writeValueAsString(map);

        mockMvc.perform(post("/comment/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
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
        String id = "7efc44ce-7a43-4820-a042-bdce1593b8b9";

        mockMvc.perform(delete("/comment/delete")
                        .param("id", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(comment))
                .andExpect(status().is(200));
    }



}
