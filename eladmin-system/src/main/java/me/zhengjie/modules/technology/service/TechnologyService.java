package me.zhengjie.modules.technology.service;

import me.zhengjie.modules.technology.domain.Technology;
import me.zhengjie.modules.technology.service.dto.TechnologyDTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

/**
* @author jie
* @date 2019-03-12
*/
@CacheConfig(cacheNames = "technology")
public interface TechnologyService {

    /**
     * findById
     * @param id
     * @return
     */
    @Cacheable(key = "#p0")
    TechnologyDTO findById(Long id);

    /**
     * create
     * @param resources
     * @return
     */
    @CacheEvict(allEntries = true)
    TechnologyDTO create(Technology resources);

    /**
     * update
     * @param resources
     */
    @CacheEvict(allEntries = true)
    void update(Technology resources);

    /**
     * delete
     * @param id
     */
    @CacheEvict(allEntries = true)
    void delete(Long id);
}