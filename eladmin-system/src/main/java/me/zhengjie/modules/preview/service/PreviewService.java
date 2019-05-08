package me.zhengjie.modules.preview.service;

import me.zhengjie.modules.preview.domain.Preview;
import me.zhengjie.modules.preview.service.dto.PreviewDTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

/**
* @author jie
* @date 2019-05-07
*/
@CacheConfig(cacheNames = "preview")
public interface PreviewService {

    /**
     * findById
     * @param id
     * @return
     */
    @Cacheable(key = "#p0")
    PreviewDTO findById(Integer id);

    /**
     * create
     * @param resources
     * @return
     */
    @CacheEvict(allEntries = true)
    PreviewDTO create(Preview resources);

    /**
     * update
     * @param resources
     */
    @CacheEvict(allEntries = true)
    void update(Preview resources);

    /**
     * delete
     * @param id
     */
    @CacheEvict(allEntries = true)
    void delete(Long id);
}