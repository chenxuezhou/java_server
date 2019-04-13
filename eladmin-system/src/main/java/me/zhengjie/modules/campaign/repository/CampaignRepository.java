package me.zhengjie.modules.campaign.repository;

import me.zhengjie.modules.campaign.domain.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author jie
* @date 2019-03-12
*/
public interface CampaignRepository extends JpaRepository<Campaign, Long>, JpaSpecificationExecutor {
}