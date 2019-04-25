package me.zhengjie.modules.courselist.service;

import me.zhengjie.modules.courselist.domain.Courselist;
import me.zhengjie.modules.courselist.service.dto.CourselistDTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

/**
* @author jie
* @date 2019-04-24
*/
@CacheConfig(cacheNames = "courselist")
public interface CourselistService {

    /**
     * findById
     * @param id
     * @return
     */
    @Cacheable(key = "#p0")
    CourselistDTO findById(Integer id);

    /**
     * create
     * @param resources
     * @return
     */
    @CacheEvict(allEntries = true)
    CourselistDTO create(Courselist resources);

    /**
     * update
     * @param resources
     */
    @CacheEvict(allEntries = true)
    void update(Courselist resources);

    /**
     * delete
     * @param id
     */
    @CacheEvict(allEntries = true)
    void delete(Long id);
}