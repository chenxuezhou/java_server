package me.zhengjie.modules.customers.service;

import me.zhengjie.modules.customers.domain.Customers;
import me.zhengjie.modules.customers.service.dto.CustomersDTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

/**
* @author jie
* @date 2019-04-24
*/
@CacheConfig(cacheNames = "customers")
public interface CustomersService {

    /**
     * findById
     * @param id
     * @return
     */
    @Cacheable(key = "#p0")
    CustomersDTO findById(Integer id);

    /**
     * create
     * @param resources
     * @return
     */
    @CacheEvict(allEntries = true)
    CustomersDTO create(Customers resources);

    /**
     * update
     * @param resources
     */
    @CacheEvict(allEntries = true)
    void update(Customers resources);

    /**
     * delete
     * @param id
     */
    @CacheEvict(allEntries = true)
    void delete(Long id);
}