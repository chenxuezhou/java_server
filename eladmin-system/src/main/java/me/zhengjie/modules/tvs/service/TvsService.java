package me.zhengjie.modules.tvs.service;

import me.zhengjie.modules.tvs.domain.Tvs;
import me.zhengjie.modules.tvs.service.dto.TvsDTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

/**
* @author jie
* @date 2019-05-07
*/
@CacheConfig(cacheNames = "tvs")
public interface TvsService {

    /**
     * findById
     * @param id
     * @return
     */
    @Cacheable(key = "#p0")
    TvsDTO findById(Integer id);

    /**
     * create
     * @param resources
     * @return
     */
    @CacheEvict(allEntries = true)
    TvsDTO create(Tvs resources);

    /**
     * update
     * @param resources
     */
    @CacheEvict(allEntries = true)
    void update(Tvs resources);

    /**
     * delete
     * @param id
     */
    @CacheEvict(allEntries = true)
    void delete(Long id);
}