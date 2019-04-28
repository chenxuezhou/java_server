package me.zhengjie.modules.exercise.service.impl;

import me.zhengjie.modules.exercise.domain.Exercise;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.modules.exercise.repository.ExerciseRepository;
import me.zhengjie.modules.exercise.service.ExerciseService;
import me.zhengjie.modules.exercise.service.dto.ExerciseDTO;
import me.zhengjie.modules.exercise.service.mapper.ExerciseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

/**
* @author jie
* @date 2019-04-28
*/
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ExerciseServiceImpl implements ExerciseService {

    @Autowired
    private ExerciseRepository exerciseRepository;

    @Autowired
    private ExerciseMapper exerciseMapper;

    @Override
    public ExerciseDTO findById(Long id) {
        Optional<Exercise> exercise = exerciseRepository.findById(id);
        ValidationUtil.isNull(exercise,"Exercise","id",id);
        return exerciseMapper.toDto(exercise.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ExerciseDTO create(Exercise resources) {
        return exerciseMapper.toDto(exerciseRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Exercise resources) {
        Optional<Exercise> optionalExercise = exerciseRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalExercise,"Exercise","id",resources.getId());

        Exercise exercise = optionalExercise.get();
        // 此处需自己修改
        resources.setId(exercise.getId());
        exerciseRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        exerciseRepository.deleteById(id);
    }
}