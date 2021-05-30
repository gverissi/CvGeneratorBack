package com.example.cv.repository;

import com.example.cv.entity.Skill;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {

    @EntityGraph(attributePaths = "curriculums")
    List<Skill> findAllByTypeOrderByName(String type);

}
