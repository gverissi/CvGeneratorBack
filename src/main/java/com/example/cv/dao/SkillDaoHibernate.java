package com.example.cv.dao;

import com.example.cv.entity.Skill;
import com.example.cv.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SkillDaoHibernate implements SkillDaoInterface {

    private final SkillRepository skillRepository;

    @Autowired
    public SkillDaoHibernate(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    @Override
    public List<Skill> findAll() {
        return skillRepository.findAll();
    }

    @Override
    public Skill findById(long id) {
        return skillRepository.getById(id);
    }

}
