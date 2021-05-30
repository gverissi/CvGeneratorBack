package com.example.cv.dao;

import com.example.cv.entity.Skill;

import java.util.List;

public interface SkillDaoInterface {

    List<Skill> findAll();

    Skill findById(long id);

}
