package com.example.cv.repository;

import com.example.cv.entity.CurriculumVitae;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurriculumRepository extends JpaRepository<CurriculumVitae, Long> {

}
