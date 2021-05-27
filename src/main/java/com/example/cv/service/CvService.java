package com.example.cv.service;

import com.example.cv.entity.CurriculumVitae;
import com.example.cv.repository.CurriculumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CvService {

    private final CurriculumRepository cvRepository;

    @Autowired
    public CvService(CurriculumRepository cvRepository) {
        this.cvRepository = cvRepository;
    }

    public CurriculumVitae findById(long id) {
        return cvRepository.findById(id).orElse(null);
    }

}
