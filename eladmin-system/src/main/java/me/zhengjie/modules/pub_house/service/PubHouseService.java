package me.zhengjie.modules.pub_house.service;

import me.zhengjie.modules.pub_house.domain.PubHouse;
import me.zhengjie.modules.pub_house.service.dto.PubHouseDTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

/**
* @author jie
* @date 2019-05-14
*/
@CacheConfig(cacheNames = "pubHouse")
public interface PubHouseService {

    /**
     * findById
     * @param id
     * @return
     */
    @Cacheable(key = "#p0")
    PubHouseDTO findById(Long id);

    /**
     * create
     * @param resources
     * @return
     */
    @CacheEvict(allEntries = true)
    PubHouseDTO create(PubHouse resources);

    /**
     * update
     * @param resources
     */
    @CacheEvict(allEntries = true)
    void update(PubHouse resources);

    /**
     * delete
     * @param id
     */
    @CacheEvict(allEntries = true)
    void delete(Long id);
}