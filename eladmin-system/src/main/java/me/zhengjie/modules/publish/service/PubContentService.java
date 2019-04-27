package me.zhengjie.modules.publish.service;

import me.zhengjie.modules.publish.domain.PubContent;
import me.zhengjie.modules.publish.service.dto.PubContentDTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

/**
* @author jie
* @date 2019-04-26
*/
@CacheConfig(cacheNames = "pubContent")
public interface PubContentService {

    /**
     * findById
     * @param id
     * @return
     */
    @Cacheable(key = "#p0")
    PubContentDTO findById(Long id);

    /**
     * create
     * @param resources
     * @return
     */
    @CacheEvict(allEntries = true)
    PubContentDTO create(PubContent resources);

    /**
     * update
     * @param resources
     */
    @CacheEvict(allEntries = true)
    void update(PubContent resources);

    /**
     * delete
     * @param id
     */
    @CacheEvict(allEntries = true)
    void delete(Long id);
}