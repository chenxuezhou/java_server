package me.zhengjie.modules.collect.service;

import me.zhengjie.modules.collect.domain.Collect;
import me.zhengjie.modules.collect.service.dto.CollectDTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

/**
* @author jie
* @date 2019-04-27
*/
@CacheConfig(cacheNames = "collect")
public interface CollectService {

    /**
     * findById
     * @param id
     * @return
     */
    @Cacheable(key = "#p0")
    CollectDTO findById(Long id);

    /**
     * create
     * @param resources
     * @return
     */
    @CacheEvict(allEntries = true)
    CollectDTO create(Collect resources);

    /**
     * update
     * @param resources
     */
    @CacheEvict(allEntries = true)
    void update(Collect resources);

    /**
     * delete
     * @param id
     */
    @CacheEvict(allEntries = true)
    void delete(Long id);
}