package com.example.cv.dao;

import com.example.cv.entity.CurriculumVitae;
import com.example.cv.repository.CurriculumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CvDaoHibernate implements CvDaoInterface {

    private final CurriculumRepository cvRepository;

    @Autowired
    public CvDaoHibernate(CurriculumRepository cvRepository) {
        this.cvRepository = cvRepository;
    }

    @Override
    public CurriculumVitae save(CurriculumVitae cv) {
        return cvRepository.save(cv);
    }

    @Override
    public List<CurriculumVitae> findAll() {
        return cvRepository.findAll();
    }

    @Override
    public CurriculumVitae findById(long id) {
        return cvRepository.findById(id).orElse(null);
    }

}
