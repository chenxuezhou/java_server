package me.zhengjie.modules.colmovie.service;

import me.zhengjie.modules.colmovie.domain.Colmovie;
import me.zhengjie.modules.colmovie.service.dto.ColmovieDTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

/**
* @author jie
* @date 2019-05-07
*/
@CacheConfig(cacheNames = "colmovie")
public interface ColmovieService {

    /**
     * findById
     * @param id
     * @return
     */
    @Cacheable(key = "#p0")
    ColmovieDTO findById(Integer id);

    /**
     * create
     * @param resources
     * @return
     */
    @CacheEvict(allEntries = true)
    ColmovieDTO create(Colmovie resources);

    /**
     * update
     * @param resources
     */
    @CacheEvict(allEntries = true)
    void update(Colmovie resources);

    /**
     * delete
     * @param id
     */
    @CacheEvict(allEntries = true)
    void delete(Long id);
}