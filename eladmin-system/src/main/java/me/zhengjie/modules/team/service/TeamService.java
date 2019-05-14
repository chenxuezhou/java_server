package me.zhengjie.modules.team.service;

import me.zhengjie.modules.team.domain.Team;
import me.zhengjie.modules.team.service.dto.TeamDTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

/**
* @author jie
* @date 2019-05-14
*/
@CacheConfig(cacheNames = "team")
public interface TeamService {

    /**
     * findById
     * @param id
     * @return
     */
    @Cacheable(key = "#p0")
    TeamDTO findById(String id);

    /**
     * create
     * @param resources
     * @return
     */
    @CacheEvict(allEntries = true)
    TeamDTO create(Team resources);

    /**
     * update
     * @param resources
     */
    @CacheEvict(allEntries = true)
    void update(Team resources);

    /**
     * delete
     * @param id
     */
    @CacheEvict(allEntries = true)
    void delete(String id);
}