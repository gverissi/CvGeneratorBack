package com.example.cv.service;

import com.example.cv.dao.SkillDaoInterface;
import com.example.cv.entity.Skill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillService {

    private final SkillDaoInterface skillDao;

    @Autowired
    public SkillService(SkillDaoInterface skillDao) {
        this.skillDao = skillDao;
    }

    public List<Skill> findAll() {
        return skillDao.findAll();
    }

    public Skill findById(long skillId) {
        return skillDao.findById(skillId);
    }
}
