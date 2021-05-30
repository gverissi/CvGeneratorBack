package com.example.cv.controller;

import com.example.cv.entity.CurriculumVitae;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
class CurriculumControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void addCv() throws Exception {
        // Given
        CurriculumVitae cv = new CurriculumVitae();
        ObjectMapper objectMapper = new ObjectMapper();
        String inputJson = objectMapper.writeValueAsString(cv);
        // When
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/cvs")
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
        // Then
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

}