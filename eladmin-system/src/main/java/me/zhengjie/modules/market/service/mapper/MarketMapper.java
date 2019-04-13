package me.zhengjie.modules.market.service.mapper;

import me.zhengjie.mapper.EntityMapper;
import me.zhengjie.modules.market.domain.Market;
import me.zhengjie.modules.market.service.dto.MarketDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author jie
* @date 2019-03-12
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MarketMapper extends EntityMapper<MarketDTO, Market> {

}