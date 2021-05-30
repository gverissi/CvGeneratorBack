package com.example.cv.controller;

import com.example.cv.entity.CurriculumVitae;
import com.example.cv.entity.Skill;
import com.example.cv.service.CvService;
import com.example.cv.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/cvs")
public class SkillController {

    private final SkillService skillService;
    private final CvService cvService;

    @Autowired
    public SkillController(SkillService skillService, CvService cvService) {
        this.skillService = skillService;
        this.cvService = cvService;
    }

    @GetMapping("/skills")
    public List<Skill> getAllSkills() {
        return skillService.findAll();
    }

    @GetMapping("/{cvId}/skills")
    public List<Skill> getAllSkillsOfACv(@PathVariable long cvId) {
        return cvService.findById(cvId).getSkills();
    }

    @PostMapping(value = "/{cvId}/skills", consumes = MediaType.TEXT_PLAIN_VALUE)
    public void addSkill(@PathVariable long cvId, @RequestBody String skillId) {
        CurriculumVitae cv = cvService.findById(cvId);
        Skill skill = skillService.findById(Long.parseLong(skillId));
        cv.addSkill(skill);
        cvService.save(cv);
    }

    @DeleteMapping("/{cvId}/skills/{skillId}")
    public void addSkill(@PathVariable long cvId, @PathVariable long skillId) {
        CurriculumVitae cv = cvService.findById(cvId);
        Skill skill = skillService.findById(skillId);
        cv.getSkills().remove(skill);
        cvService.save(cv);
    }

}
