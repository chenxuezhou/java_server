package me.zhengjie.modules.chapterlist.service;

import me.zhengjie.modules.chapterlist.domain.Chapterlist;
import me.zhengjie.modules.chapterlist.service.dto.ChapterlistDTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

/**
* @author jie
* @date 2019-04-24
*/
@CacheConfig(cacheNames = "chapterlist")
public interface ChapterlistService {

    /**
     * findById
     * @param id
     * @return
     */
    @Cacheable(key = "#p0")
    ChapterlistDTO findById(Integer id);

    /**
     * create
     * @param resources
     * @return
     */
    @CacheEvict(allEntries = true)
    ChapterlistDTO create(Chapterlist resources);

    /**
     * update
     * @param resources
     */
    @CacheEvict(allEntries = true)
    void update(Chapterlist resources);

    /**
     * delete
     * @param id
     */
    @CacheEvict(allEntries = true)
    void delete(Long id);
}