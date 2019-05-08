package me.zhengjie.modules.users.service;

import me.zhengjie.modules.users.domain.Users;
import me.zhengjie.modules.users.service.dto.UsersDTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

/**
* @author jie
* @date 2019-05-07
*/
@CacheConfig(cacheNames = "users")
public interface UsersService {

    /**
     * findById
     * @param id
     * @return
     */
    @Cacheable(key = "#p0")
    UsersDTO findById(Integer id);

    /**
     * create
     * @param resources
     * @return
     */
    @CacheEvict(allEntries = true)
    UsersDTO create(Users resources);

    /**
     * update
     * @param resources
     */
    @CacheEvict(allEntries = true)
    void update(Users resources);

    /**
     * delete
     * @param id
     */
    @CacheEvict(allEntries = true)
    void delete(Long id);
}