package me.zhengjie.modules.answer.service;

import me.zhengjie.modules.answer.domain.Answer;
import me.zhengjie.modules.answer.service.dto.AnswerDTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

/**
* @author jie
* @date 2019-04-28
*/
@CacheConfig(cacheNames = "answer")
public interface AnswerService {

    /**
     * findById
     * @param id
     * @return
     */
    @Cacheable(key = "#p0")
    AnswerDTO findById(Long id);

    /**
     * create
     * @param resources
     * @return
     */
    @CacheEvict(allEntries = true)
    AnswerDTO create(Answer resources);

    /**
     * update
     * @param resources
     */
    @CacheEvict(allEntries = true)
    void update(Answer resources);

    /**
     * delete
     * @param id
     */
    @CacheEvict(allEntries = true)
    void delete(Long id);
}