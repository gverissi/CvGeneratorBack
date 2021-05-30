package com.example.cv.controller;

import com.example.cv.entity.CurriculumVitae;
import com.example.cv.entity.Experience;
import com.example.cv.service.CvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/cvs/{cvId}")
public class ExperienceController {

    private final CvService cvService;

    @Autowired
    public ExperienceController(CvService cvService) {
        this.cvService = cvService;
    }

    @GetMapping("/experiences")
    public List<Experience> getAllExperiences(@PathVariable long cvId) {
        return cvService.findById(cvId).getExperiences();
    }

    @PostMapping("/experiences")
    public Experience addExperience(@PathVariable long cvId, @RequestBody Experience experience) {
        CurriculumVitae cv = cvService.findById(cvId);
        cv.addExperience(experience);
        cvService.save(cv);
        return cv.getExperiences().get(cv.getExperiences().size() - 1);
    }

    @PutMapping("/experiences/{expId}")
    public void updateExperience(@PathVariable long cvId, @PathVariable long expId, @RequestBody Experience experience) {
        CurriculumVitae cv = cvService.findById(cvId);
        Experience localExp = cv.getExperiences().stream().filter(exp -> exp.getId() == expId).findFirst().orElse(null);
        if (Collections.replaceAll(cv.getExperiences(), localExp, experience)) cvService.save(cv);
    }

}
