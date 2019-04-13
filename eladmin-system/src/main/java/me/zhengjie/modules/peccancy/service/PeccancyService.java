package me.zhengjie.modules.peccancy.service;

import me.zhengjie.modules.peccancy.domain.Peccancy;
import me.zhengjie.modules.peccancy.service.dto.PeccancyDTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

/**
* @author jie
* @date 2019-03-12
*/
@CacheConfig(cacheNames = "peccancy")
public interface PeccancyService {

    /**
     * findById
     * @param id
     * @return
     */
    @Cacheable(key = "#p0")
    PeccancyDTO findById(Long id);

    /**
     * create
     * @param resources
     * @return
     */
    @CacheEvict(allEntries = true)
    PeccancyDTO create(Peccancy resources);

    /**
     * update
     * @param resources
     */
    @CacheEvict(allEntries = true)
    void update(Peccancy resources);

    /**
     * delete
     * @param id
     */
    @CacheEvict(allEntries = true)
    void delete(Long id);
}