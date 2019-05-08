package me.zhengjie.modules.userlog.service;

import me.zhengjie.modules.userlog.domain.Userlog;
import me.zhengjie.modules.userlog.service.dto.UserlogDTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

/**
* @author jie
* @date 2019-05-07
*/
@CacheConfig(cacheNames = "userlog")
public interface UserlogService {

    /**
     * findById
     * @param id
     * @return
     */
    @Cacheable(key = "#p0")
    UserlogDTO findById(Integer id);

    /**
     * create
     * @param resources
     * @return
     */
    @CacheEvict(allEntries = true)
    UserlogDTO create(Userlog resources);

    /**
     * update
     * @param resources
     */
    @CacheEvict(allEntries = true)
    void update(Userlog resources);

    /**
     * delete
     * @param id
     */
    @CacheEvict(allEntries = true)
    void delete(Long id);
}