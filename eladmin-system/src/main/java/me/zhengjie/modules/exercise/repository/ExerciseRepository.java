package me.zhengjie.modules.exercise.repository;

import me.zhengjie.modules.exercise.domain.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author jie
* @date 2019-04-28
*/
public interface ExerciseRepository extends JpaRepository<Exercise, Long>, JpaSpecificationExecutor {
}