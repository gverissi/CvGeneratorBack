package com.example.cv.controller;

import com.example.cv.entity.CurriculumVitae;
import com.example.cv.entity.Experience;
import com.example.cv.repository.CurriculumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/cvs/{cvId}")
public class ExperienceController {

    private final CurriculumRepository cvRepository;

    @Autowired
    public ExperienceController(CurriculumRepository cvRepository) {
        this.cvRepository = cvRepository;
    }

    @GetMapping("/experiences")
    public List<Experience> getAllExperiences(@PathVariable long cvId) {
        CurriculumVitae cv = cvRepository.findById(cvId).orElse(null);
        List<Experience> experiences = null;
        if (cv != null) experiences = cv.getExperiences();
        return experiences;
    }

    @PostMapping("/experiences")
    Experience addExperience(@PathVariable long cvId, @RequestBody Experience experience) {
        CurriculumVitae cv = cvRepository.findById(cvId).orElse(null);
        Experience savedExp = null;
        if (cv != null) {
            cv.addExperience(experience);
            cvRepository.save(cv);
            savedExp = cv.getExperiences().get(cv.getExperiences().size() - 1);
        }
        return savedExp;
    }

    @PutMapping("/experiences/{expId}")
    void updateExperience(@PathVariable long cvId, @PathVariable long expId, @RequestBody Experience experience) {
        CurriculumVitae cv = cvRepository.findById(cvId).orElse(null);
        if (cv != null) {
            Experience localExp = cv.getExperiences().stream().filter(exp -> exp.getId() == expId).findFirst().orElse(null);
            if (Collections.replaceAll(cv.getExperiences(), localExp, experience)) cvRepository.save(cv);
        }
    }

}
