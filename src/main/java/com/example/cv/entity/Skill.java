package com.example.cv.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String type;
    private String name;

    @JsonBackReference
//    @JsonManagedReference
    @ManyToMany(mappedBy = "skills", fetch = FetchType.LAZY)
    private List<CurriculumVitae> curriculums = new ArrayList<>();

    public Skill() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CurriculumVitae> getCurriculums() {
        return curriculums;
    }

    public void setCurriculums(List<CurriculumVitae> curriculums) {
        this.curriculums = curriculums;
    }

}
