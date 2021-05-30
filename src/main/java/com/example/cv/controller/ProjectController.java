package com.example.cv.controller;

import com.example.cv.entity.CurriculumVitae;
import com.example.cv.entity.Project;
import com.example.cv.service.CvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/cvs/{cvId}")
public class ProjectController {

    private final CvService cvService;

    @Autowired
    public ProjectController(CvService cvService) {
        this.cvService = cvService;
    }

    @GetMapping("/projects")
    public List<Project> getAllExperiences(@PathVariable long cvId) {
        return cvService.findById(cvId).getProjects();
    }

    @PostMapping("/projects")
    public Project addExperience(@PathVariable long cvId, @RequestBody Project project) {
        CurriculumVitae cv = cvService.findById(cvId);
        cv.addProject(project);
        cvService.save(cv);
        return cv.getProjects().get(cv.getProjects().size() - 1);
    }

    @PutMapping("/projects/{projId}")
    public void updateExperience(@PathVariable long cvId, @PathVariable long projId, @RequestBody Project project) {
        CurriculumVitae cv = cvService.findById(cvId);
        Project localProj = cv.getProjects().stream().filter(proj -> proj.getId() == projId).findFirst().orElse(null);
        if (Collections.replaceAll(cv.getProjects(), localProj, project)) cvService.save(cv);
    }

    @DeleteMapping("/projects/{projId}")
    public void removeExperience(@PathVariable long cvId, @PathVariable long projId) {
        CurriculumVitae cv = cvService.findById(cvId);
        Project project = cv.getProjects().stream().filter(proj -> proj.getId() == projId).findFirst().orElse(null);
        cv.getProjects().remove(project);
        cvService.save(cv);
    }

}
