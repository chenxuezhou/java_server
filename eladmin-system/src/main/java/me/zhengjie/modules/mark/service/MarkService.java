package me.zhengjie.modules.mark.service;

import me.zhengjie.modules.mark.domain.Mark;
import me.zhengjie.modules.mark.service.dto.MarkDTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

/**
* @author jie
* @date 2019-05-14
*/
@CacheConfig(cacheNames = "mark")
public interface MarkService {

    /**
     * findById
     * @param id
     * @return
     */
    @Cacheable(key = "#p0")
    MarkDTO findById(String id);

    /**
     * create
     * @param resources
     * @return
     */
    @CacheEvict(allEntries = true)
    MarkDTO create(Mark resources);

    /**
     * update
     * @param resources
     */
    @CacheEvict(allEntries = true)
    void update(Mark resources);

    /**
     * delete
     * @param id
     */
    @CacheEvict(allEntries = true)
    void delete(String id);
}