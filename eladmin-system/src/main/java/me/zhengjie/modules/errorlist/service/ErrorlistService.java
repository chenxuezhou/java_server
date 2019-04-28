package me.zhengjie.modules.errorlist.service;

import me.zhengjie.modules.errorlist.domain.Errorlist;
import me.zhengjie.modules.errorlist.service.dto.ErrorlistDTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

/**
* @author jie
* @date 2019-04-28
*/
@CacheConfig(cacheNames = "errorlist")
public interface ErrorlistService {

    /**
     * findById
     * @param id
     * @return
     */
    @Cacheable(key = "#p0")
    ErrorlistDTO findById(Integer id);

    /**
     * create
     * @param resources
     * @return
     */
    @CacheEvict(allEntries = true)
    ErrorlistDTO create(Errorlist resources);

    /**
     * update
     * @param resources
     */
    @CacheEvict(allEntries = true)
    void update(Errorlist resources);

    /**
     * delete
     * @param id
     */
    @CacheEvict(allEntries = true)
    void delete(Long id);
}