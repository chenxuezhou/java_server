package me.zhengjie.modules.teacher.service;

import me.zhengjie.modules.teacher.domain.Teacher;
import me.zhengjie.modules.teacher.service.dto.TeacherDTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

/**
* @author jie
* @date 2019-04-24
*/
@CacheConfig(cacheNames = "teacher")
public interface TeacherService {

    /**
     * findById
     * @param id
     * @return
     */
    @Cacheable(key = "#p0")
    TeacherDTO findById(Integer id);

    /**
     * create
     * @param resources
     * @return
     */
    @CacheEvict(allEntries = true)
    TeacherDTO create(Teacher resources);

    /**
     * update
     * @param resources
     */
    @CacheEvict(allEntries = true)
    void update(Teacher resources);

    /**
     * delete
     * @param id
     */
    @CacheEvict(allEntries = true)
    void delete(Long id);
}