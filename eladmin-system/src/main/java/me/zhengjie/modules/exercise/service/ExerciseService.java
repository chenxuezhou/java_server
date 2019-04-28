package me.zhengjie.modules.exercise.service;

import me.zhengjie.modules.exercise.domain.Exercise;
import me.zhengjie.modules.exercise.service.dto.ExerciseDTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

/**
* @author jie
* @date 2019-04-28
*/
@CacheConfig(cacheNames = "exercise")
public interface ExerciseService {

    /**
     * findById
     * @param id
     * @return
     */
    @Cacheable(key = "#p0")
    ExerciseDTO findById(Long id);

    /**
     * create
     * @param resources
     * @return
     */
    @CacheEvict(allEntries = true)
    ExerciseDTO create(Exercise resources);

    /**
     * update
     * @param resources
     */
    @CacheEvict(allEntries = true)
    void update(Exercise resources);

    /**
     * delete
     * @param id
     */
    @CacheEvict(allEntries = true)
    void delete(Long id);
}