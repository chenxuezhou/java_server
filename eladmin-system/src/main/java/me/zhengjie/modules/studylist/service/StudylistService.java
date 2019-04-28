package me.zhengjie.modules.studylist.service;

import me.zhengjie.modules.studylist.domain.Studylist;
import me.zhengjie.modules.studylist.service.dto.StudylistDTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

/**
* @author jie
* @date 2019-04-28
*/
@CacheConfig(cacheNames = "studylist")
public interface StudylistService {

    /**
     * findById
     * @param id
     * @return
     */
    @Cacheable(key = "#p0")
    StudylistDTO findById(Integer id);

    /**
     * create
     * @param resources
     * @return
     */
    @CacheEvict(allEntries = true)
    StudylistDTO create(Studylist resources);

    /**
     * update
     * @param resources
     */
    @CacheEvict(allEntries = true)
    void update(Studylist resources);

    /**
     * delete
     * @param id
     */
    @CacheEvict(allEntries = true)
    void delete(Long id);
}