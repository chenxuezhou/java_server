package me.zhengjie.modules.courseschedule.service;

import me.zhengjie.modules.courseschedule.domain.Courseschedule;
import me.zhengjie.modules.courseschedule.service.dto.CoursescheduleDTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

/**
* @author jie
* @date 2019-05-14
*/
@CacheConfig(cacheNames = "courseschedule")
public interface CoursescheduleService {

    /**
     * findById
     * @param id
     * @return
     */
    @Cacheable(key = "#p0")
    CoursescheduleDTO findById(String id);

    /**
     * create
     * @param resources
     * @return
     */
    @CacheEvict(allEntries = true)
    CoursescheduleDTO create(Courseschedule resources);

    /**
     * update
     * @param resources
     */
    @CacheEvict(allEntries = true)
    void update(Courseschedule resources);

    /**
     * delete
     * @param id
     */
    @CacheEvict(allEntries = true)
    void delete(String id);
}