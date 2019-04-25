package me.zhengjie.modules.intro.service;

import me.zhengjie.modules.intro.domain.Intro;
import me.zhengjie.modules.intro.service.dto.IntroDTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

/**
* @author jie
* @date 2019-04-24
*/
@CacheConfig(cacheNames = "intro")
public interface IntroService {

    /**
     * findById
     * @param id
     * @return
     */
    @Cacheable(key = "#p0")
    IntroDTO findById(Integer id);

    /**
     * create
     * @param resources
     * @return
     */
    @CacheEvict(allEntries = true)
    IntroDTO create(Intro resources);

    /**
     * update
     * @param resources
     */
    @CacheEvict(allEntries = true)
    void update(Intro resources);

    /**
     * delete
     * @param id
     */
    @CacheEvict(allEntries = true)
    void delete(Long id);
}