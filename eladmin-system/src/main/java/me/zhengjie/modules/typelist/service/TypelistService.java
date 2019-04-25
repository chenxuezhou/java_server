package me.zhengjie.modules.typelist.service;

import me.zhengjie.modules.typelist.domain.Typelist;
import me.zhengjie.modules.typelist.service.dto.TypelistDTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

/**
* @author jie
* @date 2019-04-24
*/
@CacheConfig(cacheNames = "typelist")
public interface TypelistService {

    /**
     * findById
     * @param id
     * @return
     */
    @Cacheable(key = "#p0")
    TypelistDTO findById(Integer id);

    /**
     * create
     * @param resources
     * @return
     */
    @CacheEvict(allEntries = true)
    TypelistDTO create(Typelist resources);

    /**
     * update
     * @param resources
     */
    @CacheEvict(allEntries = true)
    void update(Typelist resources);

    /**
     * delete
     * @param id
     */
    @CacheEvict(allEntries = true)
    void delete(Long id);
}