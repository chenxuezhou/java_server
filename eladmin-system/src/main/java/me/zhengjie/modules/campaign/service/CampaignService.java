package me.zhengjie.modules.campaign.service;

import me.zhengjie.modules.campaign.domain.Campaign;
import me.zhengjie.modules.campaign.service.dto.CampaignDTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

/**
* @author jie
* @date 2019-03-12
*/
@CacheConfig(cacheNames = "campaign")
public interface CampaignService {

    /**
     * findById
     * @param id
     * @return
     */
    @Cacheable(key = "#p0")
    CampaignDTO findById(Long id);

    /**
     * create
     * @param resources
     * @return
     */
    @CacheEvict(allEntries = true)
    CampaignDTO create(Campaign resources);

    /**
     * update
     * @param resources
     */
    @CacheEvict(allEntries = true)
    void update(Campaign resources);

    /**
     * delete
     * @param id
     */
    @CacheEvict(allEntries = true)
    void delete(Long id);
}