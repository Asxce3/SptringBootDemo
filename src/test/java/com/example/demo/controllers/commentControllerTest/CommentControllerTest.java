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

        String userId = "e1322807-e5c6-45bb-bf9d-89f84b5b745c";
        String restaurantId = "33066665-fecd-46d8-873b-cb7498e9a55b";

        map.put("person_id", userId);
        map.put("restaurantId", restaurantId);
        map.put("comment", "Ужаснове заведение");
        map.put("score", "1");

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
        String id = "c219f18a-2cee-4bd3-b87f-c4e456c971e9";

        mockMvc.perform(delete("/comment/delete")
                        .param("id", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(comment))
                .andExpect(status().is(200));
    }



}
