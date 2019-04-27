package me.zhengjie.modules.sub.service;

import me.zhengjie.modules.sub.domain.Sub;
import me.zhengjie.modules.sub.service.dto.SubDTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

/**
* @author jie
* @date 2019-04-27
*/
@CacheConfig(cacheNames = "sub")
public interface SubService {

    /**
     * findById
     * @param id
     * @return
     */
    @Cacheable(key = "#p0")
    SubDTO findById(Long id);

    /**
     * create
     * @param resources
     * @return
     */
    @CacheEvict(allEntries = true)
    SubDTO create(Sub resources);

    /**
     * update
     * @param resources
     */
    @CacheEvict(allEntries = true)
    void update(Sub resources);

    /**
     * delete
     * @param id
     */
    @CacheEvict(allEntries = true)
    void delete(Long id);
}