package com.example.cv.controller;

import com.example.cv.entity.CurriculumVitae;
import com.example.cv.entity.Person;
import com.example.cv.repository.CurriculumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/cvs/{cvId}")
public class PersonController {

    private final CurriculumRepository cvRepository;

    @Autowired
    public PersonController(CurriculumRepository cvRepository) {
        this.cvRepository = cvRepository;
    }

    @GetMapping("/person")
    public Person getPerson(@PathVariable long cvId) {
        CurriculumVitae cv = cvRepository.findById(cvId).orElse(null);
        Person person = null;
        if (cv != null) person = cv.getPerson();
        return person;
    }

    @PostMapping("/person")
    Person addPerson(@PathVariable long cvId, @RequestBody Person person) {
        CurriculumVitae cv = cvRepository.findById(cvId).orElse(null);
        if (cv != null) {
            cv.setPerson(person);
            cvRepository.save(cv);
        }
        return person;
    }

}
