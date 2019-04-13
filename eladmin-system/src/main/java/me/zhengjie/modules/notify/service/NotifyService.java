package me.zhengjie.modules.notify.service;

import me.zhengjie.modules.notify.domain.Notify;
import me.zhengjie.modules.notify.service.dto.NotifyDTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

/**
* @author jie
* @date 2019-03-12
*/
@CacheConfig(cacheNames = "notify")
public interface NotifyService {

    /**
     * findById
     * @param id
     * @return
     */
    @Cacheable(key = "#p0")
    NotifyDTO findById(Long id);

    /**
     * create
     * @param resources
     * @return
     */
    @CacheEvict(allEntries = true)
    NotifyDTO create(Notify resources);

    /**
     * update
     * @param resources
     */
    @CacheEvict(allEntries = true)
    void update(Notify resources);

    /**
     * delete
     * @param id
     */
    @CacheEvict(allEntries = true)
    void delete(Long id);
}