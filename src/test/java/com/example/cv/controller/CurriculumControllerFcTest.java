package com.example.cv.controller;

import com.example.cv.entity.CurriculumVitae;
import com.example.cv.repository.CurriculumRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicLong;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
class CurriculumControllerFcTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CurriculumRepository curriculumRepository;

    @Test
    void addCv() throws Exception {
        // Given
        CurriculumVitae cv = new CurriculumVitae();
        cv.setName("My beautifully CV");
        ObjectMapper objectMapper = new ObjectMapper();
        String inputJson = objectMapper.writeValueAsString(cv);
        // When
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/cvs")
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
        // Then
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        String[] splitContent = content.split(",");
        AtomicLong id = new AtomicLong();
        Arrays.stream(splitContent).filter(s -> s.contains("id")).findFirst().ifPresent(s -> id.set(Long.parseLong(s.split(":")[1])));
        CurriculumVitae savedCv = curriculumRepository.findById(id.get()).orElseThrow();
        assertEquals(cv.getName(), savedCv.getName());
    }

}