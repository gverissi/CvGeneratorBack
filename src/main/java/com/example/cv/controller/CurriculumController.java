package com.example.cv.controller;

import com.example.cv.entity.CurriculumVitae;
import com.example.cv.repository.CurriculumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CurriculumController {

    private final CurriculumRepository userRepository;

    @Autowired
    public CurriculumController(CurriculumRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/cvs")
    public List<CurriculumVitae> getUsers() {
        return userRepository.findAll();
    }

    @PostMapping("/cvs")
    void addUser(@RequestBody CurriculumVitae cv) {
        userRepository.save(cv);
    }

}
