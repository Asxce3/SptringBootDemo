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


//   "{\"PersonUuid\":\"ad177f61-196e-4ccf-ae08-135ec2d4c7de\"}";
    @Test
        public void createCommentTest() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> map = new HashMap<>();
// person : ad177f61-196e-4ccf-ae08-135ec2d4c7de
// restaurant : 8805bb43-eea9-48a5-b287-07ad1d5fec4f
        map.put("personUuid", "ad177f61-196e-4ccf-ae08-135ec2d4c7de");
        map.put("restaurantUuid", "8805bb43-eea9-48a5-b287-07ad1d5fec4f");
        map.put("comment", "Отличное заведение");
        map.put("score", "5");

        String comment = objectMapper.writeValueAsString(map);

        mockMvc.perform(post("/comment/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(comment))
                .andExpect(status().is(404));
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

        mockMvc.perform(delete("/comment/delete")
                        .param("uuid", "43d9d0e8-d4ad-41a6-be54-820dba8f6789")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(comment))
                .andExpect(status().is(200));
    }



}
