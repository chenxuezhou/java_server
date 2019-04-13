package me.zhengjie.modules.meet.service;

import me.zhengjie.modules.meet.domain.Meeting;
import me.zhengjie.modules.meet.service.dto.MeetingDTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

/**
* @author jie
* @date 2019-04-13
*/
@CacheConfig(cacheNames = "meeting")
public interface MeetingService {

    /**
     * findById
     * @param id
     * @return
     */
    @Cacheable(key = "#p0")
    MeetingDTO findById(Long id);

    /**
     * create
     * @param resources
     * @return
     */
    @CacheEvict(allEntries = true)
    MeetingDTO create(Meeting resources);

    /**
     * update
     * @param resources
     */
    @CacheEvict(allEntries = true)
    void update(Meeting resources);

    /**
     * delete
     * @param id
     */
    @CacheEvict(allEntries = true)
    void delete(Long id);
}