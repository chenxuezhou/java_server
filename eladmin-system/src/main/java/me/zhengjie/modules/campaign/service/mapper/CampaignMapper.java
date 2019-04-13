package me.zhengjie.modules.campaign.service.mapper;

import me.zhengjie.mapper.EntityMapper;
import me.zhengjie.modules.campaign.domain.Campaign;
import me.zhengjie.modules.campaign.service.dto.CampaignDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author jie
* @date 2019-03-12
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CampaignMapper extends EntityMapper<CampaignDTO, Campaign> {

}