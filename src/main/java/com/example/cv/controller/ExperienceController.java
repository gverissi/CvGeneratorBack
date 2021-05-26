package com.example.cv.controller;

import com.example.cv.entity.CurriculumVitae;
import com.example.cv.entity.Experience;
import com.example.cv.repository.CurriculumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    void addExperience(@PathVariable long cvId, @RequestBody Experience experience) {
        CurriculumVitae cv = cvRepository.findById(cvId).orElse(null);
        if (cv != null) {
            cv.getExperiences().add(experience);
            cvRepository.save(cv);
        }
    }

}
