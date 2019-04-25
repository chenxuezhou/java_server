package me.zhengjie.modules.rate.service;

import me.zhengjie.modules.rate.domain.Rate;
import me.zhengjie.modules.rate.service.dto.RateDTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

/**
* @author jie
* @date 2019-04-24
*/
@CacheConfig(cacheNames = "rate")
public interface RateService {

    /**
     * findById
     * @param id
     * @return
     */
    @Cacheable(key = "#p0")
    RateDTO findById(Integer id);

    /**
     * create
     * @param resources
     * @return
     */
    @CacheEvict(allEntries = true)
    RateDTO create(Rate resources);

    /**
     * update
     * @param resources
     */
    @CacheEvict(allEntries = true)
    void update(Rate resources);

    /**
     * delete
     * @param id
     */
    @CacheEvict(allEntries = true)
    void delete(Long id);
}