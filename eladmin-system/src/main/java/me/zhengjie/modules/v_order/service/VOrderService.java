package me.zhengjie.modules.v_order.service;

import me.zhengjie.modules.v_order.domain.VOrder;
import me.zhengjie.modules.v_order.service.dto.VOrderDTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

/**
* @author jie
* @date 2019-04-29
*/
@CacheConfig(cacheNames = "vOrder")
public interface VOrderService {

    /**
     * findById
     * @param id
     * @return
     */
    @Cacheable(key = "#p0")
    VOrderDTO findById(Long id);

    /**
     * create
     * @param resources
     * @return
     */
    @CacheEvict(allEntries = true)
    VOrderDTO create(VOrder resources);

    /**
     * update
     * @param resources
     */
    @CacheEvict(allEntries = true)
    void update(VOrder resources);

    /**
     * delete
     * @param id
     */
    @CacheEvict(allEntries = true)
    void delete(Long id);
}