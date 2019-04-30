package me.zhengjie.modules.dish.service;

import me.zhengjie.modules.dish.domain.Dish;
import me.zhengjie.modules.dish.service.dto.DishDTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

/**
* @author jie
* @date 2019-04-30
*/
@CacheConfig(cacheNames = "dish")
public interface DishService {

    /**
     * findById
     * @param id
     * @return
     */
    @Cacheable(key = "#p0")
    DishDTO findById(Long id);

    /**
     * create
     * @param resources
     * @return
     */
    @CacheEvict(allEntries = true)
    DishDTO create(Dish resources);

    /**
     * update
     * @param resources
     */
    @CacheEvict(allEntries = true)
    void update(Dish resources);

    /**
     * delete
     * @param id
     */
    @CacheEvict(allEntries = true)
    void delete(Long id);
}