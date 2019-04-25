package me.zhengjie.modules.reclist.service;

import me.zhengjie.modules.reclist.domain.Reclist;
import me.zhengjie.modules.reclist.service.dto.ReclistDTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

/**
* @author jie
* @date 2019-04-24
*/
@CacheConfig(cacheNames = "reclist")
public interface ReclistService {

    /**
     * findById
     * @param id
     * @return
     */
    @Cacheable(key = "#p0")
    ReclistDTO findById(Integer id);

    /**
     * create
     * @param resources
     * @return
     */
    @CacheEvict(allEntries = true)
    ReclistDTO create(Reclist resources);

    /**
     * update
     * @param resources
     */
    @CacheEvict(allEntries = true)
    void update(Reclist resources);

    /**
     * delete
     * @param id
     */
    @CacheEvict(allEntries = true)
    void delete(Long id);
}