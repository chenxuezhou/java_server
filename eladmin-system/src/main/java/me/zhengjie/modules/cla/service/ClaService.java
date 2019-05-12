package me.zhengjie.modules.cla.service;

import me.zhengjie.modules.cla.domain.Cla;
import me.zhengjie.modules.cla.service.dto.ClaDTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

/**
* @author jie
* @date 2019-05-12
*/
@CacheConfig(cacheNames = "cla")
public interface ClaService {

    /**
     * findById
     * @param id
     * @return
     */
    @Cacheable(key = "#p0")
    ClaDTO findById(Long id);

    /**
     * create
     * @param resources
     * @return
     */
    @CacheEvict(allEntries = true)
    ClaDTO create(Cla resources);

    /**
     * update
     * @param resources
     */
    @CacheEvict(allEntries = true)
    void update(Cla resources);

    /**
     * delete
     * @param id
     */
    @CacheEvict(allEntries = true)
    void delete(Long id);
}