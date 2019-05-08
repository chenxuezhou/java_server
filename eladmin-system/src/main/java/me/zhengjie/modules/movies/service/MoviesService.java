package me.zhengjie.modules.movies.service;

import me.zhengjie.modules.movies.domain.Movies;
import me.zhengjie.modules.movies.service.dto.MoviesDTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

/**
* @author jie
* @date 2019-05-07
*/
@CacheConfig(cacheNames = "movies")
public interface MoviesService {

    /**
     * findById
     * @param id
     * @return
     */
    @Cacheable(key = "#p0")
    MoviesDTO findById(Integer id);

    /**
     * create
     * @param resources
     * @return
     */
    @CacheEvict(allEntries = true)
    MoviesDTO create(Movies resources);

    /**
     * update
     * @param resources
     */
    @CacheEvict(allEntries = true)
    void update(Movies resources);

    /**
     * delete
     * @param id
     */
    @CacheEvict(allEntries = true)
    void delete(Long id);
}