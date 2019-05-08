package me.zhengjie.modules.comments.service;

import me.zhengjie.modules.comments.domain.Comments;
import me.zhengjie.modules.comments.service.dto.CommentsDTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

/**
* @author jie
* @date 2019-05-07
*/
@CacheConfig(cacheNames = "comments")
public interface CommentsService {

    /**
     * findById
     * @param id
     * @return
     */
    @Cacheable(key = "#p0")
    CommentsDTO findById(Integer id);

    /**
     * create
     * @param resources
     * @return
     */
    @CacheEvict(allEntries = true)
    CommentsDTO create(Comments resources);

    /**
     * update
     * @param resources
     */
    @CacheEvict(allEntries = true)
    void update(Comments resources);

    /**
     * delete
     * @param id
     */
    @CacheEvict(allEntries = true)
    void delete(Long id);
}