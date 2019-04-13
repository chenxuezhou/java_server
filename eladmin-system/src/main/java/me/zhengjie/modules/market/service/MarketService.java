package me.zhengjie.modules.market.service;

import me.zhengjie.modules.market.domain.Market;
import me.zhengjie.modules.market.service.dto.MarketDTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

/**
* @author jie
* @date 2019-03-12
*/
@CacheConfig(cacheNames = "market")
public interface MarketService {

    /**
     * findById
     * @param id
     * @return
     */
    @Cacheable(key = "#p0")
    MarketDTO findById(Long id);

    /**
     * create
     * @param resources
     * @return
     */
    @CacheEvict(allEntries = true)
    MarketDTO create(Market resources);

    /**
     * update
     * @param resources
     */
    @CacheEvict(allEntries = true)
    void update(Market resources);

    /**
     * delete
     * @param id
     */
    @CacheEvict(allEntries = true)
    void delete(Long id);
}