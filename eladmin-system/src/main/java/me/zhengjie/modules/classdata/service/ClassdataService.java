package me.zhengjie.modules.classdata.service;

import me.zhengjie.modules.classdata.domain.Classdata;
import me.zhengjie.modules.classdata.service.dto.ClassdataDTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

/**
* @author jie
* @date 2019-04-24
*/
@CacheConfig(cacheNames = "classdata")
public interface ClassdataService {

    /**
     * findById
     * @param id
     * @return
     */
    @Cacheable(key = "#p0")
    ClassdataDTO findById(Integer id);

    /**
     * create
     * @param resources
     * @return
     */
    @CacheEvict(allEntries = true)
    ClassdataDTO create(Classdata resources);

    /**
     * update
     * @param resources
     */
    @CacheEvict(allEntries = true)
    void update(Classdata resources);

    /**
     * delete
     * @param id
     */
    @CacheEvict(allEntries = true)
    void delete(Long id);
}