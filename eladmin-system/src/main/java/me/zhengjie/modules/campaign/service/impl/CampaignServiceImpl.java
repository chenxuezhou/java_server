package me.zhengjie.modules.campaign.service.impl;

import me.zhengjie.modules.campaign.domain.Campaign;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.modules.campaign.repository.CampaignRepository;
import me.zhengjie.modules.campaign.service.CampaignService;
import me.zhengjie.modules.campaign.service.dto.CampaignDTO;
import me.zhengjie.modules.campaign.service.mapper.CampaignMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

/**
* @author jie
* @date 2019-03-12
*/
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class CampaignServiceImpl implements CampaignService {

    @Autowired
    private CampaignRepository campaignRepository;

    @Autowired
    private CampaignMapper campaignMapper;

    @Override
    public CampaignDTO findById(Long id) {
        Optional<Campaign> campaign = campaignRepository.findById(id);
        ValidationUtil.isNull(campaign,"Campaign","id",id);
        return campaignMapper.toDto(campaign.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CampaignDTO create(Campaign resources) {
        return campaignMapper.toDto(campaignRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Campaign resources) {
        Optional<Campaign> optionalCampaign = campaignRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalCampaign,"Campaign","id",resources.getId());

        Campaign campaign = optionalCampaign.get();
        // 此处需自己修改
        resources.setId(campaign.getId());
        campaignRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        campaignRepository.deleteById(id);
    }
}