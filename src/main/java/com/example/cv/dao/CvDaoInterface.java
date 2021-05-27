package com.example.cv.dao;

import com.example.cv.entity.CurriculumVitae;

import java.util.List;

public interface CvDaoInterface {

    CurriculumVitae save(CurriculumVitae cv);

    List<CurriculumVitae> findAll();

    CurriculumVitae findById(long id);

}
