package com.example.cv.controller;

import com.example.cv.entity.CurriculumVitae;
import com.example.cv.entity.Information;
import com.example.cv.entity.Person;
import com.example.cv.service.CvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/cvs/{cvId}")
public class InformationController {

    private final CvService cvService;

    @Autowired
    public InformationController(CvService cvService) {
        this.cvService = cvService;
    }

    @GetMapping("/information")
    public Information getInformation(@PathVariable long cvId) {
        return cvService.findById(cvId).getInformation();
    }

    @PostMapping("/information")
    public Information setInformation(@PathVariable long cvId, @RequestBody Information information) {
        CurriculumVitae cv = cvService.findById(cvId);
        cv.setInformation(information);
        cvService.save(cv);
        return information;
    }

}
