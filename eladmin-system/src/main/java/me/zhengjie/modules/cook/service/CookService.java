package me.zhengjie.modules.cook.service;

import me.zhengjie.modules.cook.domain.Cook;
import me.zhengjie.modules.cook.service.dto.CookDTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

/**
* @author jie
* @date 2019-04-30
*/
@CacheConfig(cacheNames = "cook")
public interface CookService {

    /**
     * findById
     * @param id
     * @return
     */
    @Cacheable(key = "#p0")
    CookDTO findById(Long id);

    /**
     * create
     * @param resources
     * @return
     */
    @CacheEvict(allEntries = true)
    CookDTO create(Cook resources);

    /**
     * update
     * @param resources
     */
    @CacheEvict(allEntries = true)
    void update(Cook resources);

    /**
     * delete
     * @param id
     */
    @CacheEvict(allEntries = true)
    void delete(Long id);
}