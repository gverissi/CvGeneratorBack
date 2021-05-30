package com.example.cv.controller;

import com.example.cv.entity.CurriculumVitae;
import com.example.cv.service.CvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CurriculumController {

    private final CvService cvService;

    @Autowired
    public CurriculumController(CvService cvService) {
        this.cvService = cvService;
    }

    @GetMapping("/cvs")
    public List<CurriculumVitae> getCvs() {
        return cvService.findAll();
    }

    @GetMapping("/cvs/{cvId}")
    public CurriculumVitae getCv(@PathVariable long cvId) {
        return cvService.findById(cvId);
    }

    @PostMapping("/cvs")
    public CurriculumVitae addCv(@RequestBody CurriculumVitae cv) {
        System.out.println("dans addCv");
        System.out.println(cv);
        return cvService.save(cv);
    }

    @PutMapping("/cvs/{cvId}")
    public void updateCv(@PathVariable long cvId, @RequestBody CurriculumVitae cv) {
        if (cvId != 0) {
            cvService.save(cv);
        }
    }

}
