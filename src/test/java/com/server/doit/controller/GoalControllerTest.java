package com.server.doit.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.server.doit.controller.dto.GoalDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON;


@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class GoalControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void createGoal() throws Exception {
        mockMvc.perform(post("/api/goal/create")
                .contentType(MediaType.asMediaType(APPLICATION_JSON))
                .content(objectMapper.writeValueAsString(
                        new GoalDto("Goal1", "Cat1", 1519442460L, 1519442460L, 1, 1, 1, true, 3, "#992233"))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.goalName", is("Goal1")))
                .andExpect(jsonPath("$.category", is("Cat1")))
                .andExpect(jsonPath("$.epochStartDate", is(1519442460)));
    }

    @Test
    public void failToCreateGoal() throws Exception {
        mockMvc.perform(post("/api/goal/create")
                .contentType(MediaType.asMediaType(APPLICATION_JSON))
                .content(objectMapper.writeValueAsString(
                        new GoalDto("Goal1"))))
                .andExpect(status().is4xxClientError());
    }
}
