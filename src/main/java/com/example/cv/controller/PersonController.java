package com.example.cv.controller;

import com.example.cv.entity.CurriculumVitae;
import com.example.cv.entity.Person;
import com.example.cv.service.CvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/cvs/{cvId}")
public class PersonController {

    private final CvService cvService;

    @Autowired
    public PersonController(CvService cvService) {
        this.cvService = cvService;
    }

    @GetMapping("/person")
    public Person getPerson(@PathVariable long cvId) {
        return cvService.findById(cvId).getPerson();
    }

    @PostMapping("/person")
    Person addPerson(@PathVariable long cvId, @RequestBody Person person) {
        CurriculumVitae cv = cvService.findById(cvId);
        cv.setPerson(person);
        cvService.save(cv);
        return person;
    }

}
