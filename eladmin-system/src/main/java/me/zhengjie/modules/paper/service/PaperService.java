package me.zhengjie.modules.paper.service;

import me.zhengjie.modules.paper.domain.Paper;
import me.zhengjie.modules.paper.service.dto.PaperDTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

/**
* @author jie
* @date 2019-03-12
*/
@CacheConfig(cacheNames = "paper")
public interface PaperService {

    /**
     * findById
     * @param id
     * @return
     */
    @Cacheable(key = "#p0")
    PaperDTO findById(Long id);

    /**
     * create
     * @param resources
     * @return
     */
    @CacheEvict(allEntries = true)
    PaperDTO create(Paper resources);

    /**
     * update
     * @param resources
     */
    @CacheEvict(allEntries = true)
    void update(Paper resources);

    /**
     * delete
     * @param id
     */
    @CacheEvict(allEntries = true)
    void delete(Long id);
}