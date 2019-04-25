package me.zhengjie.modules.course_class.service;

import me.zhengjie.modules.course_class.domain.CourseClass;
import me.zhengjie.modules.course_class.service.dto.CourseClassDTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

/**
* @author jie
* @date 2019-04-24
*/
@CacheConfig(cacheNames = "courseClass")
public interface CourseClassService {

    /**
     * findById
     * @param id
     * @return
     */
    @Cacheable(key = "#p0")
    CourseClassDTO findById(Integer id);

    /**
     * create
     * @param resources
     * @return
     */
    @CacheEvict(allEntries = true)
    CourseClassDTO create(CourseClass resources);

    /**
     * update
     * @param resources
     */
    @CacheEvict(allEntries = true)
    void update(CourseClass resources);

    /**
     * delete
     * @param id
     */
    @CacheEvict(allEntries = true)
    void delete(Long id);
}