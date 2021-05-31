package com.example.cv.controller;

import com.example.cv.entity.CurriculumVitae;
import com.example.cv.entity.Formation;
import com.example.cv.service.CvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/cvs/{cvId}")
public class FormationController {

    private final CvService cvService;

    @Autowired
    public FormationController(CvService cvService) {
        this.cvService = cvService;
    }

    @GetMapping("/formations")
    public List<Formation> getAllFormations(@PathVariable long cvId) {
        return cvService.findById(cvId).getFormations();
    }

    @PostMapping("/formations")
    public Formation addFormation(@PathVariable long cvId, @RequestBody Formation formation) {
        CurriculumVitae cv = cvService.findById(cvId);
        cv.addFormation(formation);
        cvService.save(cv);
        return cv.getFormations().get(cv.getFormations().size() - 1);
    }

    @PutMapping("/formations/{formationId}")
    public void updateFormation(@PathVariable long cvId, @PathVariable long formationId, @RequestBody Formation formation) {
        CurriculumVitae cv = cvService.findById(cvId);
        Formation localFormation = cv.getFormations().stream().filter(form -> form.getId() == formationId).findFirst().orElse(null);
        if (Collections.replaceAll(cv.getFormations(), localFormation, formation)) cvService.save(cv);
    }

    @DeleteMapping("/formations/{formationId}")
    public void removeFormation(@PathVariable long cvId, @PathVariable long formationId) {
        CurriculumVitae cv = cvService.findById(cvId);
        Formation formation = cv.getFormations().stream().filter(form -> form.getId() == formationId).findFirst().orElse(null);
        cv.getFormations().remove(formation);
        cvService.save(cv);
    }

}
