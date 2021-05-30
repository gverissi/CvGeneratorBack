package com.example.cv.controller;

import com.example.cv.entity.CurriculumVitae;
import com.example.cv.repository.CurriculumRepository;
import com.example.cv.repository.SkillRepository;
import com.example.cv.service.CvService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@WebMvcTest(controllers = CurriculumController.class)
class CurriculumControllerUnitTest {

    @MockBean
    private CvService cvServiceMock;

    @MockBean
    private CurriculumRepository curriculumRepository;

    @MockBean
    private SkillRepository skillRepository;

    private CurriculumController curriculumController;

    @BeforeEach
    void init() {
        curriculumController = new CurriculumController(cvServiceMock);
        when(cvServiceMock.save(any(CurriculumVitae.class))).thenAnswer(invocationOnMock -> invocationOnMock.getArguments()[0]);
    }

    @Test
    void addCv() {
        // Given
        CurriculumVitae cv = new CurriculumVitae();
        // When
        CurriculumVitae createdCv = curriculumController.addCv(cv);
        // Then
        verify(cvServiceMock).save(cv);
        assertEquals(cv, createdCv);
    }

}