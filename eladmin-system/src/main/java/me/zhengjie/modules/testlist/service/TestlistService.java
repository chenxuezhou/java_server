package me.zhengjie.modules.testlist.service;

import me.zhengjie.modules.testlist.domain.Testlist;
import me.zhengjie.modules.testlist.service.dto.TestlistDTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

/**
* @author jie
* @date 2019-04-28
*/
@CacheConfig(cacheNames = "testlist")
public interface TestlistService {

    /**
     * findById
     * @param id
     * @return
     */
    @Cacheable(key = "#p0")
    TestlistDTO findById(Integer id);

    /**
     * create
     * @param resources
     * @return
     */
    @CacheEvict(allEntries = true)
    TestlistDTO create(Testlist resources);

    /**
     * update
     * @param resources
     */
    @CacheEvict(allEntries = true)
    void update(Testlist resources);

    /**
     * delete
     * @param id
     */
    @CacheEvict(allEntries = true)
    void delete(Long id);
}