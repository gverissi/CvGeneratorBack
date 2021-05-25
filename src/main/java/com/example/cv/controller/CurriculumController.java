package com.example.cv.controller;

import com.example.cv.entity.CurriculumVitae;
import com.example.cv.repository.CurriculumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CurriculumController {

    private final CurriculumRepository cvRepository;

    @Autowired
    public CurriculumController(CurriculumRepository cvRepository) {
        this.cvRepository = cvRepository;
    }

    @GetMapping("/cvs")
    public List<CurriculumVitae> getCvs() {
        return cvRepository.findAll();
    }

    @PostMapping("/cvs")
    void addCv(@RequestBody CurriculumVitae cv) {
        cvRepository.save(cv);
    }

}
