package com.example.cv.service;

import com.example.cv.dao.CvDaoInterface;
import com.example.cv.entity.CurriculumVitae;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CvService {

    private final CvDaoInterface cvDao;

    @Autowired
    public CvService(CvDaoInterface cvDao) {
        this.cvDao = cvDao;
    }

    public CurriculumVitae save(CurriculumVitae cv) {
        return cvDao.save(cv);
    }

    public List<CurriculumVitae> findAll() {
        return cvDao.findAll();
    }

    public CurriculumVitae findById(long id) {
        return cvDao.findById(id);
    }

}
