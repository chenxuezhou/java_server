package me.zhengjie.modules.address.service;

import me.zhengjie.modules.address.domain.Address;
import me.zhengjie.modules.address.service.dto.AddressDTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

/**
* @author jie
* @date 2019-04-29
*/
@CacheConfig(cacheNames = "address")
public interface AddressService {

    /**
     * findById
     * @param id
     * @return
     */
    @Cacheable(key = "#p0")
    AddressDTO findById(Long id);

    /**
     * create
     * @param resources
     * @return
     */
    @CacheEvict(allEntries = true)
    AddressDTO create(Address resources);

    /**
     * update
     * @param resources
     */
    @CacheEvict(allEntries = true)
    void update(Address resources);

    /**
     * delete
     * @param id
     */
    @CacheEvict(allEntries = true)
    void delete(Long id);
}